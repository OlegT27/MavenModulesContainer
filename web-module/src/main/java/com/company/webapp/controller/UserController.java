
package com.company.webapp.controller;


import com.company.webapp.user.entity.User;
import com.company.webapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView onIndexPage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("currentUser", new User());
        model.addObject("userToSubmit", new User());
        return model;
    }

    @RequestMapping("/users")
    public ModelAndView onUsersList() {
        ModelAndView model = onIndexPage();
        model.addObject("usersList", userService.requestUsersList());
        return model;
    }

    @PostMapping("/add")
    public String onAddUser(@ModelAttribute("userToSubmit") User user, BindingResult result) {
        if (!userService.submitUser(user, result))
            return "index";
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String onDeleteUser(@ModelAttribute("currentUser") User user) {
        userService.deleteUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    ModelAndView onUpdatePage(@RequestParam int id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        model.addObject("userToUpdate", userService.getUserToEdit(new User(id)));
        return model;
    }

    @PostMapping("/update_user")
    ModelAndView onUpdateUser(@ModelAttribute("userToUpdate") User user, BindingResult result) {
        ModelAndView model = new ModelAndView("user");
        if (!userService.updateUser(user, result))
            model.addObject("userToUpdate", user);
        else
            model.addObject("userToUpdate", userService.getUserToEdit(user));
        return model;
    }

}
