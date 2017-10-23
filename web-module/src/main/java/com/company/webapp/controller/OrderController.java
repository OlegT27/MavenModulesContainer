package com.company.webapp.controller;

import com.company.webapp.entity.Order;
import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.OrderDataManager;
import com.company.webapp.service.viewmaker.ViewMaker;
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
    @Autowired
    private ViewMaker viewMaker;

    @RequestMapping("/orders")
    ModelAndView onOrdersPage(@ModelAttribute("userId") int key) {

        return viewMaker.getOrdersPage(key, "orders");
    }

    @PostMapping("/add_order")
    String onAddOrder(@ModelAttribute("orderToAdd") Order order, @ModelAttribute("userId") int key) {
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
    public int createSessionUserId(@ModelAttribute("currentUser") User user) {
        return user.getId();
    }
}
