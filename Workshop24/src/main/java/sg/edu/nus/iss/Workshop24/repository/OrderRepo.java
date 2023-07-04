package sg.edu.nus.iss.Workshop24.repository;

import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop24.model.OrderDetails;
import sg.edu.nus.iss.Workshop24.model.Orders;

@Repository
public class OrderRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from orders";
    private final String findByIdSQL = "select * from orders where id = ?";
    private final String insertSQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?)";
    private final String insertOrderDetailsSQL = "insert into order_details (product, unit_price, discount, quantity, order_id) values (?, ?, ?, ?, ?)";

    // this method enables you to throw custom exception
    public Orders findOrderById(Integer orderId){
        List<Orders> orders = jdbcTemplate.query(findByIdSQL, BeanPropertyRowMapper.newInstance(Orders.class), orderId);

        if(orders.isEmpty()){
            // throw custom exception
            throw new ResolutionException("Order not found.");
        }

        return orders.get(0);
    }

    public List<Orders> findAllOrders(){
        List<Orders> orders = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Orders.class));

        if (orders.isEmpty()){
            // throw custom exception
            throw new ResolutionException("No orders found.");
        }

        return orders;
    }

    public Boolean createOrder(Orders order){
        int result = jdbcTemplate.update(insertSQL, order.getOrderDate(), order.getCustomerName(),
        order.getShipAddress(), order.getNotes(), order.getTax());

        return result > 0 ? true : false;
    }

    // this method to returb back a value (in this case we getting the id)
    public Integer insertOrder(Orders order){
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSQL, new String[]{"id"});
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setDouble(5, order.getTax());
                return ps;
            }
 
        };

        jdbcTemplate.update(psc, generatedKeyHolder);

        Integer result = generatedKeyHolder.getKey().intValue();
        return result;
    }

    // batch update for order details
    public int[] add (List<OrderDetails> orderDetails){
        List <Object[]> params = orderDetails.stream()
            .map(od -> new Object[]{od.getProduct(), od.getUnitPrice(),
            od.getDiscount(), od.getQuantity(), od.getOrderId()})
            .collect(Collectors.toList());

        int added[] = jdbcTemplate.batchUpdate(insertOrderDetailsSQL, params);
        return added;
    }
}
