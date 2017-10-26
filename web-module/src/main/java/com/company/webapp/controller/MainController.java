
package com.company.webapp.controller;


import com.company.service.datamanager.UserDataManager;
import com.company.service.dataproducer.UserValidator;
import com.company.webapp.entity.User;
import com.company.webapp.viewmaker.ViewMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserDataManager userDataManager;
    @Autowired
    private ViewMaker viewMaker;

    @RequestMapping("/")
    public ModelAndView onIndexPage() {
        return viewMaker.getIndexPage("index");

    }

    @RequestMapping("/users")
    public ModelAndView onUsersList() {
        ModelAndView model = viewMaker.getIndexPage("index");
        return viewMaker.getUsersList(model);
    }

    @PostMapping("/add")
    public String onAddUser(@ModelAttribute("userToSubmit") User user, BindingResult result) {

        UserValidator validator = new UserValidator();
        validator.validate(user, result);
        if (result.hasErrors())
            return "index";
        userDataManager.insertUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String onDeleteUser(@ModelAttribute("currentUser") User user) {
        userDataManager.markUserNotExist(user);
        return "redirect:/";
    }

}
