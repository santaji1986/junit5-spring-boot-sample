package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/junit5")
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false)
    String name) {
        return "Hello " + name;
    }
}
