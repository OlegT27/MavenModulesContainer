
package com.company.webapp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserHiberService userService;
    @Autowired
    private UserValidator validator;

    @RequestMapping("/")
    public ModelAndView onIndexPage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("currentUser", new User());
        return model;
    }

    @RequestMapping("/users")
    public ModelAndView onUsersList() {
        ModelAndView model = onIndexPage();
        model.addObject("usersList", userService.requestUsersList());
        return model;
    }

    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String onAddUser(@Validated @RequestBody User user, BindingResult result) {
        //if (!isValid(user, result))
        if (result.hasErrors())
            return "index";
        if (userService.createUser(user))
            return "index";
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String onDeleteUser(@ModelAttribute("currentUser") User user) {
        userService.deleteUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    ModelAndView onUpdatePage(@RequestParam Long id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        User userToEdit = new User();
        userToEdit.setId(id);
        model.addObject("userToUpdate", userService.getUserToEdit(userToEdit));
        return model;
    }

    @PostMapping("/update_user")
    ModelAndView onUpdateUser(@ModelAttribute("userToUpdate") @Valid User user, BindingResult result) {
        ModelAndView model = new ModelAndView("user");
        if (!result.hasErrors())
            if (userService.updateUser(user)) {
                model.addObject("userToUpdate", userService.getUserToEdit(user));
                return model;
            }
        return model.addObject("userToUpdate", user);
    }

    private boolean isValid(User userToValidate, BindingResult bindingResult) {
        validator.validate(userToValidate, bindingResult);
        return !bindingResult.hasErrors();
    }

}
