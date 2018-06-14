package com.zc.eurekaribbonclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    private RestTemplate restTemplate;

    @Autowired
    public RibbonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }
}
