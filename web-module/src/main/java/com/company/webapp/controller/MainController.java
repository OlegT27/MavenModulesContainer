
package com.company.webapp.controller;

import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.UserDataManager;
import com.company.webapp.service.dataproducer.UserValidator;
import com.company.webapp.service.viewmaker.IndexViewMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserDataManager userDataManager;
    @Autowired
    private IndexViewMaker viewMaker;

    @RequestMapping("/")
    public ModelAndView getIndexPage() {
        return viewMaker.initIndexPage("index");

    }

    @RequestMapping("/users")
    public ModelAndView getUsersList() {
        ModelAndView model = viewMaker.initIndexPage("index");
        return viewMaker.getUsersList(model);
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("userToSubmit") User user, BindingResult result) {

        UserValidator validator = new UserValidator();
        validator.validate(user, result);
        if (result.hasErrors())
            return "index";
        userDataManager.insertUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("currentUser") User user) {
        userDataManager.markUserNotExist(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    ModelAndView getUpdateView(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userToUpdate", userDataManager.getUserById(id));
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping("/update_user")
    ModelAndView updateUser(@ModelAttribute("userToUpdate") User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        UserValidator validator = new UserValidator();
        validator.validate(user, result);
        if (!result.hasErrors())
            userDataManager.updateUser(user);
        modelAndView.setViewName("user");
        return modelAndView;


    }

}
