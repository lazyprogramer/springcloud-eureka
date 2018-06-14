package com.zc.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ", I am from port: " + port;
    }
}
