package org.phdljr.springwebsocketstomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
