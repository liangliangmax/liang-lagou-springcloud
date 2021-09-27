package com.liang.lagou.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/register")
    public String register(){

        return "register";
    }

    @RequestMapping("/welcome")
    public String welcome(){

        return "welcome";
    }
}
