
package com.company.webapp.controller;

import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.OrderDataManager;
import com.company.webapp.service.datamanager.UserDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserDataManager userDataManager;
    @Autowired
    private OrderDataManager orderDataManager;


    @GetMapping("/")
    public ModelAndView onGet() {
        System.out.println("catch get");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("userToAdd", new User());
        modelAndView.addObject("userToDel", new User());
        modelAndView.addObject("usersList", userDataManager.getAllExistUsers());
        return modelAndView;
    }

    @PostMapping("/add")
    public String onPost(@ModelAttribute("userToAdd") User user) {
        System.out.println("catch post add");
        userDataManager.insertUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String onDelete(@ModelAttribute("userToDel") User user) {
        System.out.println("catch post del");
        // get empty user! fail
        userDataManager.markUserNotExist(user);
        return "redirect:/";
    }

    @RequestMapping("/update")
    ModelAndView onUpdate(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userToUpdate", userDataManager.getUserById(id));
        modelAndView.addObject("ordersList", orderDataManager.getOrderByUser(id));
        modelAndView.setViewName("service");

        return modelAndView;
    }
}
