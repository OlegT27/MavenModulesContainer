package com.company.webapp.controller;

import com.company.webapp.entity.Order;
import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.OrderDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private OrderDataManager orderDataManager;

    @RequestMapping("/orders")
    ModelAndView getOrdersListView(@ModelAttribute("userId") int key) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersList", orderDataManager.getOrderByUser(key));
        modelAndView.addObject("orderToAdd", new Order());
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @PostMapping("/add_order")
    String addOrder(@ModelAttribute("orderToAdd") Order order, @ModelAttribute("userId") int key) {
        order.setUserId(key);
        orderDataManager.insertOrder(order);
        return "forward:/orders";
    }

    @RequestMapping("/back")
    String clearSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute("userId")
    public int createSessionUser(@ModelAttribute("currentUser") User user) {
        return user.getId();
    }
}
