package com.embarx.FirstSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return  "Hello Bhaskar !!! ";
    }

    @PostMapping("/hello")
    public String helloName(@RequestBody String name){

        return  "Hello "+ name +" !!!";
    }
}
