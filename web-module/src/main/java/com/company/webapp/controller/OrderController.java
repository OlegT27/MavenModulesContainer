package com.company.webapp.controller;


import com.company.webapp.order.entity.Order;
import com.company.webapp.order.service.OrderService;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("userId")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    ModelAndView onOrdersPage(@ModelAttribute("userId") int key) {
        ModelAndView model = new ModelAndView();
        model.setViewName("orders");
        model.addObject("orderToAdd", new Order());
        return model;
    }

    @RequestMapping("/orders_list")
    ModelAndView onOrderList(@ModelAttribute("userId") int key) {
        ModelAndView model = onOrdersPage(key);
        model.addObject("ordersList", orderService.getOrdersList(new User(key)));
        return model;
    }

    @PostMapping("/add_order")
    String onAddOrder(@ModelAttribute("userId") int key, @ModelAttribute("orderToAdd") Order order, BindingResult result) {
        order.setUserId(key);
        if (!orderService.createOrder(order, result))
            return "orders";
        return "redirect:/orders";
    }

    @RequestMapping("/back")
    String clearSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute("userId")
    public int createSessionUserId(@ModelAttribute("currentUser") User user) {
        return user.getId();
    }
}
