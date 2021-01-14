package io.javajason.springsecurityjwt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloResource {

    @RequestMapping({"/hello"})
    public String Hello(){
        return "Hello World";
    }
    
}
