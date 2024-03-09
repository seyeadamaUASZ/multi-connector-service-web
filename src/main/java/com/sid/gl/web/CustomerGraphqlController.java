package com.sid.gl.web;

import com.sid.gl.dto.CustomerRequest;
import com.sid.gl.dto.CustomerResponse;
import com.sid.gl.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphqlController {
    private CustomerService customerService;

    @QueryMapping
    public List<CustomerResponse> allCustomers(){
        return customerService.findAll();
    }

    @QueryMapping
public CustomerResponse customerById(@Argument Long id){
        return customerService.findCustomerById(id);
 }

 @MutationMapping
 public CustomerResponse saveCustomer(@Argument CustomerRequest customerRequest){
      return customerService.saveCustomer(customerRequest);
 }
}
