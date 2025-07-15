package com.exa.secure.HelloController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(HttpServletRequest request) {
        return "Hello, World!" + request.getSession().getId();
    }

}
