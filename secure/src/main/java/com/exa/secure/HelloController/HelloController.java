package com.exa.secure.HelloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exa.secure.Student;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class HelloController {
@Autowired
@SuppressWarnings("unused")
    AuthenticationManager authenticationManager;
@PostMapping("/hello")
public String sayHello(HttpServletRequest request) {
    return "Hello, World!" + request.getSession().getId();
}

@GetMapping("/csrf")
public String getCsrfToken(@RequestParam String param) {
    return "CSRF token for " + param;
}

@PostMapping("/login")
public String Login(@RequestBody Student student) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(student.getName(), String.valueOf(student.getId())));
    if(authentication.isAuthenticated()) {
        return generateToken(student);
    } else {
        System.out.println("Authentication failed");
        return "Authentication failed";
    }
}

// Dummy implementation for generateToken
private String generateToken(Student student) {
    // In a real application, generate and return a JWT or session token here
    return "token_for_" + student.getName();
}
    
    

}
