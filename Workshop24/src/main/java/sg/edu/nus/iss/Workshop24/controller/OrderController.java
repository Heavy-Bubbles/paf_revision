package sg.edu.nus.iss.Workshop24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop24.payload.OrderRequest;
import sg.edu.nus.iss.Workshop24.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired 
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequest orderRequest){
        Boolean result = orderService.makeOrder(orderRequest.getOrder(), orderRequest.getOrderDetails());

        return ResponseEntity.ok().body(result);
    }
    
}
