package com.zc.eurekaribbonclient.controller;

import com.zc.eurekaribbonclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    private RibbonService ribbonService;
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public  RibbonController(RibbonService ribbonService, LoadBalancerClient loadBalancerClient) {
        this.ribbonService = ribbonService;
        this.loadBalancerClient = loadBalancerClient;
    }

    @GetMapping("/hi")
    public String hi(@RequestParam(required = false, defaultValue = "zc") String name) {
        return ribbonService.hi(name);
    }

    @GetMapping("/testRibbon")
    public String testRibbon() {
        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        return instance.getHost() + ":" + instance.getPort();
    }
}
