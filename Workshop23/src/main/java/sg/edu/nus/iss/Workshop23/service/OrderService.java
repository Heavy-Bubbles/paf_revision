package sg.edu.nus.iss.Workshop23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop23.model.Order;
import sg.edu.nus.iss.Workshop23.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired 
    OrderRepo orderRepo;

    public Order getOrder(int id){
        Order order = orderRepo.getOrderById(id);
        order.setTotalPrice(orderRepo.getTotalPrice(order));
        order.setProducts(orderRepo.getProducts(order));
        order.setTotalCostPrice(orderRepo.getTotalCostPrice(order));
        return order;
    }
}
