package com.zc.eurekafeignclient.controller;

import com.zc.eurekafeignclient.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    private HiService service;
    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "zc", required = false)String name) {
        return service.sayHi(name);
    }
}
