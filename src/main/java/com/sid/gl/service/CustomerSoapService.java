package com.sid.gl.service;

import com.sid.gl.dto.CustomerRequest;
import com.sid.gl.dto.CustomerResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {
    private CustomerService customerService;

    @WebMethod
    public List<CustomerResponse> customerList(){
        return customerService.findAll();
    }

    @WebMethod
    public CustomerResponse getCustomer(@WebParam(name="id") Long id){
        return customerService.findCustomerById(id);
    }

    @WebMethod
    public CustomerResponse saveCustomer(@WebParam(name="customer") CustomerRequest request){
        return customerService.saveCustomer(request);
    }
}
