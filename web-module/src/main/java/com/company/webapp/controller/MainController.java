
package com.company.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/welcome")
    public String onGet() {
        System.out.println("catch get");
        return "index";
    }

    @PostMapping("/welcome")
    public String onPost() {
        System.out.println("catch post");
        return "index";
    }
}
