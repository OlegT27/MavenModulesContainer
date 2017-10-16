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

@SessionAttributes("orderUser")
@Controller
public class OrderController {

    @Autowired
    private OrderDataManager orderDataManager;

    @RequestMapping("/orders")
    ModelAndView getOrdersListView(@ModelAttribute("orderUser") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersList", orderDataManager.getOrderByUser(user.getId()));
        modelAndView.addObject("orderUser", user);
        Order orderToAdd = new Order();
        orderToAdd.setUserId(user.getId());
        modelAndView.addObject("orderToAdd", orderToAdd);
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @PostMapping("/add_order")
    String addOrder(@ModelAttribute("orderToAdd") Order order) {
        orderDataManager.insertOrder(order);
        return "forward:/orders";
    }

    @RequestMapping("/back")
    String clearSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "forward:/webapp";
    }

    @ModelAttribute("orderUser")
    public User createSessionUser(@ModelAttribute("currentUser") User user) {
        return user;
    }
}
