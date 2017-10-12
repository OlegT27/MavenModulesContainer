
package com.company.webapp.controller;

import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.UserDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserDataManager userDataManager;


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
}
