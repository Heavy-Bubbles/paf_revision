package sg.edu.nus.iss.workshop21.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop21.exception.ResourceNotFoundException;
import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Orders;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String getAllSQL = "select * from customers limit ? offset ?";
    private final String getByIdSQL = "select * from customers where id = ?";
    private final String getCustomerOrdersSQL = "select * from orders where customer_id = ?";

    public List<Customer> getAllCustomers(int limit, int offset){
        List<Customer> customers = new ArrayList<Customer>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(getAllSQL, limit, offset);

        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setCompany(rs.getString("company"));

            customers.add(customer);
        }

        return Collections.unmodifiableList(customers);
    }

    public Customer getCustomerById(int id){
        return jdbcTemplate.queryForObject(getByIdSQL, 
            BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    public List<Orders> getOrdersByCustomerId(int id){

        try{
            Optional<Customer> customer = Optional.of(jdbcTemplate.queryForObject(getByIdSQL, 
            BeanPropertyRowMapper.newInstance(Customer.class), id));
        } catch (Exception ex){
            throw new ResourceNotFoundException("Customer does not exist");
        }

            List<Orders> orders = new ArrayList<Orders>();

            SqlRowSet rs = jdbcTemplate.queryForRowSet(getCustomerOrdersSQL, id);

            while (rs.next()){
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getString("order_date"));
                
                orders.add(order);
            }

            return Collections.unmodifiableList(orders);
        

    }
}
