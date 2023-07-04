package sg.edu.nus.iss.Workshop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.Workshop24.model.OrderDetails;
import sg.edu.nus.iss.Workshop24.model.Orders;
import sg.edu.nus.iss.Workshop24.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired
    OrderRepo orderRepo;

    @Transactional
    public Boolean makeOrder(Orders order, List<OrderDetails> details){
        
        // 1. create new order and get the returned pk of the created order
        Integer orderId = orderRepo.insertOrder(order);

        // 2. set the fk for the order details that links to the created order
        for (OrderDetails od : details){
            od.setOrderId(orderId);
        }

        // 3. create the order details
        int [] added = orderRepo.add(details);

        // check if added 
        for (int result : added){
            if (result != 1){
                throw new IllegalArgumentException("Failed to add all order details");
            }
        }

        return true;
    }
}
