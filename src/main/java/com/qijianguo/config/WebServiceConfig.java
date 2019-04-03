package com.qijianguo.config;

import com.qijianguo.service.CustomWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
 
import javax.xml.ws.Endpoint;
 
 
/**
 * @作者 Administrator
 * @创建日期 2018/6/23 0023
 * @创建时间 11:31.
 */
@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;
 
    @Autowired
    CustomWebService service;
 
    /*jax-ws*/
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, service);
        endpoint.publish("/CustomWebService");
        return endpoint;
    }
}