
package com.company.webapp.controller;

import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.OrderDataManager;
import com.company.webapp.service.datamanager.UserDataManager;
import com.company.webapp.service.dataproducer.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserDataManager userDataManager;
    @Autowired
    private OrderDataManager orderDataManager;
    @Autowired
    private Validator validator;


    @GetMapping("/")
    public ModelAndView getUsersListView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("userToSubmit", new User());
        modelAndView.addObject("currentUser", new User());
        modelAndView.addObject("usersList", userDataManager.getAllExistUsers());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("userToSubmit") User user) {
        if (validator.dataValidator(new String[]{user.getName(), user.getPatron(), user.getSurname()}, user.getBirthDate()))
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
        List<User> users = userDataManager.getUserById(id);
        modelAndView.addObject("userToUpdate", users.get(0));
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping("/update_user")
    ModelAndView updateUser(@ModelAttribute("userToUpdate") User user) {
        ModelAndView modelAndView = new ModelAndView();
        userDataManager.updateUser(user);
        modelAndView.setViewName("user");
        return modelAndView;


    }

}
