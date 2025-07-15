package com.exa.secure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
    
    List<Student> students= new ArrayList<>(List.of(
        new Student(1,"Yassh","jAVA"),
        new Student(2,"Yash","Python")
    ));
    
    @GetMapping("/csrf-token")
    public String getCsrfToken(HttpServletRequest request) {
        return (String) request.getAttribute("csrfToken");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }
}
