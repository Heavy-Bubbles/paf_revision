package sg.edu.nus.iss.Workshop23.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop23.model.Order;
import sg.edu.nus.iss.Workshop23.model.Product;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String getOrderByIdSql = "select * from orders where id = ?;";
    private final String getTotalPriceSql = "select sum(quantity*unit_price) as total_price from order_details where order_id = ?";
    private final String getProductsSql = "select p.product_name, p.standard_cost*od.quantity as cost_price from products p join order_details od on od.product_id = p.id where od.order_id = ?";
    private final String getTotalCostPrice = "select sum(p.standard_cost*od.quantity) as total_cost from products p join order_details od on od.product_id = p.id where od.order_id = ?";

    public Order getOrderById(int id){

        Order order = new Order();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(getOrderByIdSql, id);

        while (rs.next()){
            order.setId(rs.getInt("id"));
            order.setOrderDate(rs.getString("order_date"));
            order.setCustomerId(rs.getInt("customer_id"));
        }

        return order;
    }

    public double getTotalPrice(Order order){
        return jdbcTemplate.queryForObject(getTotalPriceSql,Integer.class, order.getId());
        
    }

    public List<Product> getProducts(Order order){

        List<Product> products = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(getProductsSql, order.getId());

        while (rs.next()){
            Product product = new Product();
            product.setName(rs.getString("product_name"));
            product.setCostPrice(rs.getDouble("cost_price"));

            products.add(product);
        }

        return products;
    }

    public double getTotalCostPrice(Order order){
        return jdbcTemplate.queryForObject(getTotalCostPrice,Integer.class, order.getId());
        
    }
}
