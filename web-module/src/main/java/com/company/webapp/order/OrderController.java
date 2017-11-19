package com.company.webapp.order;


import com.company.webapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@SessionAttributes("userId")
@Controller
public class OrderController {

    @Autowired
    private OrderHiberService orderService;

    @Autowired
    private OrderValidator validator;

    @RequestMapping("/orders")
    ModelAndView onOrdersPage(@ModelAttribute("userId") Long key) {
        ModelAndView model = new ModelAndView();
        model.setViewName("orders");
        model.addObject("orderToAdd", new Order());
        return model;
    }

    @RequestMapping("/orders_list")
    ModelAndView onOrderList(@ModelAttribute("userId") Long key) {
        ModelAndView model = onOrdersPage(key);
        User user = new User();
        user.setId(key);
        model.addObject("ordersList", orderService.getOrdersList(user));
        return model;
    }

    @PostMapping("/add_order")
    String onAddOrder(@ModelAttribute("userId") Long key, @ModelAttribute("orderToAdd") @Valid Order order, BindingResult result) {
        //order.setUserId(key);
        // if (!isValid(order, result))
        if (result.hasErrors())
            return "orders";
        if (orderService.createOrder(order, key))
            return "orders";
        return "redirect:/orders";
    }

    @RequestMapping("/back")
    String clearSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute("userId")
    public Long createSessionUserId(@ModelAttribute("currentUser") User user) {
        return user.getId();
    }

    private boolean isValid(Order orderToValidate, BindingResult bindingResult) {
        validator.validate(orderToValidate, bindingResult);
        return !bindingResult.hasErrors();
    }
}
