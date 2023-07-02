package sg.edu.nus.iss.Workshop23.UIController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.Workshop23.model.Order;
import sg.edu.nus.iss.Workshop23.service.OrderService;

@Controller
@RequestMapping("/")
public class OrderUIController {

    @Autowired 
    OrderService orderService;
    
    @GetMapping
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/order/total")
    public String getOrderTotal(@RequestParam(value = "orderId", required = true) int orderId, Model model){
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order";
    }
}
