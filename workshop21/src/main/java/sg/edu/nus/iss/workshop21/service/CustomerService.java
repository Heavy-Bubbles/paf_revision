package sg.edu.nus.iss.workshop21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Orders;
import sg.edu.nus.iss.workshop21.repository.CustomerRepo;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getAllCustomers(int limit, int offset){
        return customerRepo.getAllCustomers(limit, offset);
    }

    public Customer getCustomerById(int id){
        return customerRepo.getCustomerById(id);
    }

    public List<Orders> getOrdersByCustomerId(int id){
        return customerRepo.getOrdersByCustomerId(id);
    }
}
