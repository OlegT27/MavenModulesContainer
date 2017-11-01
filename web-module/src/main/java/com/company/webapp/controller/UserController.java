package com.company.webapp.controller;


import com.company.webapp.service.datamanager.UserDataManager;
import com.company.webapp.viewmaker.ViewMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserDataManager userDataManager;
    @Autowired
    private ViewMaker viewMaker;


   /* @GetMapping("/update")
    ModelAndView onUpdatePage(@RequestParam int id) {
        return viewMaker.getUpdateView(id, "user");
    }

    @PostMapping("/update_user")
    ModelAndView onUpdateUser(@ModelAttribute("userToUpdate") User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        UserValidator validator = new UserValidator();

        validator.validate(user, result);
        if (!result.hasErrors())
            userDataManager.updateUser(user);
        modelAndView.setViewName("user");
        return modelAndView;
    }*/
}
