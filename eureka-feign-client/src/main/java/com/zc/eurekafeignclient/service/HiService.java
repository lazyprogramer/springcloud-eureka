package com.zc.eurekafeignclient.service;

import com.zc.eurekafeignclient.client.EurekaClientFeign;
import org.springframework.stereotype.Service;

@Service
public class HiService {
    private EurekaClientFeign eurekaClientFeign;
    public HiService(EurekaClientFeign eurekaClientFeign) {
        this.eurekaClientFeign = eurekaClientFeign;
    }

    public String sayHi(String name) {
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
