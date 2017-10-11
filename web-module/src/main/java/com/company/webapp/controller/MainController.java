
package com.company.webapp.controller;

import com.company.webapp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private Service userService;


    @GetMapping("/")
    public ModelAndView onGet() {
        System.out.println("catch get");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("usersList", userService.getAllExistUsers());
        return modelAndView;
    }

    @PostMapping("/")
    public String onPost() {
        System.out.println("catch post");
        return "index";
    }
}
