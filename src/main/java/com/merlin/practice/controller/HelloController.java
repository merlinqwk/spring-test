package com.merlin.practice.controller;

import com.merlin.practice.model.AdGroupSum;
import com.merlin.practice.model.Student;
import com.merlin.practice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

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

    @ResponseBody
    @PostMapping("getStoreName")
    public String getStoreName(){
        return helloService.getStoreName();
    }

    @ResponseBody
    @PostMapping("getIdResult")
    public String getIdResult(@RequestBody AdGroupSum adGroupSum){
        return helloService.getIdResult(adGroupSum);
    }
}
