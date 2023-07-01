package sg.edu.nus.iss.workshop21.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Orders;
import sg.edu.nus.iss.workshop21.service.CustomerService;

@RestController
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<String> getAllCustomers(@RequestParam(name = "limit", defaultValue = "5") int limit,
    @RequestParam(name = "offset", defaultValue = "0") int offset){
        Optional<List<Customer>> customers = Optional.of(customerService.getAllCustomers(limit, offset));

        if (customers.isEmpty()){
            return ResponseEntity.status(404).body(
                Json.createObjectBuilder()
                .add("message", "No Customers found.")
                .build().toString());
        }

        return ResponseEntity.ok(customers.get().toString());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable("customerId") int customerId){
        try{
            Customer customer = customerService.getCustomerById(customerId);
            return ResponseEntity.ok(customer.toString());
        } catch (Exception e){

            return ResponseEntity.status(404).body(
                Json.createObjectBuilder()
                .add("message", "Customer does not exist.")
                .build().toString());
        }
    
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<Orders>> getCustomerOrders(@PathVariable("customerId") int customerId){
        // Optional<Customer> customer = Optional.of(customerService.getCustomerById(customerId));

        // if(customer.isEmpty()){
        //     return ResponseEntity.status(404).body(
        //         Json.createObjectBuilder()
        //         .add("message", "Customer does not exist.")
        //         .build().toString());
        // }

        List<Orders> orders = customerService.getOrdersByCustomerId(customerId);
     
        return ResponseEntity.ok().body(orders);
    }

}
