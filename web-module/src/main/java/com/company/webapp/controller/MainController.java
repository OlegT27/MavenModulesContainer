
package com.company.webapp.controller;

import com.company.webapp.entity.User;
import com.company.webapp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private Service userService;

    @GetMapping("/welcome")
    public ModelAndView onGet() {
        System.out.println("catch get");
        List<User> usersList = userService.getAllUsers();
        System.out.println("service call from on get");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("usersList", usersList);
        userService.getAllUsers_alpha();
        System.out.println("service_my dao call from on get");
        return modelAndView;
    }

    @PostMapping("/welcome")
    public String onPost() {
        System.out.println("catch post");
        return String.valueOf(userService.addUser(new User()));
    }
}
