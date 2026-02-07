package com.J2EE.lap3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {
    @GetMapping("/home")
    public String Index(){
        return  "index";
    }
}
