package com.company.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("userId")
@Controller
public class OrderController {
/*
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
    */
}
