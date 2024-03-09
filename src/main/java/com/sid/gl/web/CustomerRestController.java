package com.sid.gl.web;

import com.sid.gl.dto.CustomerRequest;
import com.sid.gl.dto.CustomerResponse;
import com.sid.gl.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerResponse> customerList(){
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public CustomerResponse customerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }
    @PostMapping("/customers")
    public CustomerResponse saveCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.saveCustomer(customerRequest);
    }
}
