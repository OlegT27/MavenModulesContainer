package com.company.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class MainController {
    //private ServiceModule serviceModule;

    @GetMapping("/hello")
    public String helloFromSpring() {
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("springHello","hello");
        modelAndView.setViewName("spring_hello");
        return modelAndView;*/
        return "spring_hello";
    }

    @GetMapping("/mvhello")
    public ModelAndView helloMV() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("spring_hello_mv");
        return modelAndView;
    }

}
