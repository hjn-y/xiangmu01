package com.yw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ViewController {
    @RequestMapping("loginview")
    public String loginView(){
        return "login";
    }

}
