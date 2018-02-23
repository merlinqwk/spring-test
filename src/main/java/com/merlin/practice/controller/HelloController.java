package com.merlin.practice.controller;

import com.merlin.practice.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @ResponseBody
    @PostMapping("hello")
    public Student test(@RequestBody Student student){
        return student;
    }

    @ResponseBody
    @PostMapping("getStudent")
    public Student getStudent(){
        Student student = new Student(1,"Jack","boy");
        return student;
    }
}
