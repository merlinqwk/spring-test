package com.merlin.practice.controller;

import com.merlin.practice.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @ResponseBody
    @PostMapping("hello")
    public String test(@RequestBody Student student){
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + student.getId());
        sb.append("; name:" + student.getName());
        sb.append("; sex:" + student.getSex());
        return String.valueOf(sb);
    }

    @ResponseBody
    @PostMapping("getStudent")
    public Student getStudent(){
        Student student = new Student(1,"Jack","boy");
        return student;
    }
}
