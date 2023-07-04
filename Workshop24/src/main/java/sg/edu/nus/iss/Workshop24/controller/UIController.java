package sg.edu.nus.iss.Workshop24.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.Workshop24.model.OrderDetails;
import sg.edu.nus.iss.Workshop24.model.Orders;
import sg.edu.nus.iss.Workshop24.service.OrderService;

@Controller
@RequestMapping("/home")
public class UIController {
    
    @Autowired 
    OrderService orderService;

    @GetMapping
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/orderadd")
    public String newOrderForm(Model model){
        Orders order = new Orders();
        model.addAttribute("order", order);
        return "orderadd";
    }

    @PostMapping("/ordernext")
    public String postOrderForm(HttpSession session, @ModelAttribute("order")Orders order, Model model){
        session.setAttribute("order", order);
        model.addAttribute("orderDetails", new OrderDetails());
        return "orderdetailsadd";
    }

    @PostMapping("/nextdetails")
    public String postOrderDetails(HttpSession session, @ModelAttribute("orderDetails") OrderDetails orderDetails,
    Model model){
        List<OrderDetails> orderDetailsList = null;

        // create list of order details
        if (session.getAttribute("orderDetails") == null){
            orderDetailsList = new ArrayList<OrderDetails>();
            orderDetailsList.add(orderDetails);
            session.setAttribute("orderDetails", orderDetailsList);
        } else {
            orderDetailsList = (List<OrderDetails>) session.getAttribute("orderDetails");
            orderDetailsList.add(orderDetails);
            session.setAttribute("orderDetails", orderDetailsList);
        }

        model.addAttribute("orderDetails", new OrderDetails());
        return "orderdetailsadd";
    }

    @PostMapping ("/completeorder")
    public String completeOrder(HttpSession session){
        Orders order = (Orders) session.getAttribute("order");
        List<OrderDetails> orderDetails = (List<OrderDetails>) session.getAttribute("orderDetails");
        orderService.makeOrder(order, orderDetails);

        session.invalidate();

        return "welcome";

    }
}
