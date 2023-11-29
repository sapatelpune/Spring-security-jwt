package com.ms.security.jwt.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // This is secure rest url , This can be accessable only with the valid JWT token
    @GetMapping("/hello")
    public String helloWorld(){
        return "hello JWT app";
    }
}
