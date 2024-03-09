package com.sid.gl.config;

import com.sid.gl.service.CustomerSoapService;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CxfConfig {
    private Bus bus;
    private CustomerSoapService customerSoapService;

    @Bean
    public EndpointImpl endpoint(){
       EndpointImpl endpoint= new EndpointImpl(bus,customerSoapService);
       endpoint.publish("/CustomerService");
       return endpoint;
    }
}
