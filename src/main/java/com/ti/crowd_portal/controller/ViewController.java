package com.ti.crowd_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ti
 * @date 2019/2/1
 */

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }
}
