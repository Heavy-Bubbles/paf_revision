package sg.edu.nus.iss.Workshop23.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop23.model.Order;
import sg.edu.nus.iss.Workshop23.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/total")
    public ResponseEntity<Order> getOrder(@RequestParam(required = true) int id){
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }
    
}
