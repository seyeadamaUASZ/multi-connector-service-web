package com.sid.gl.mapper;

import com.sid.gl.dto.CustomerRequest;
import com.sid.gl.dto.CustomerResponse;
import com.sid.gl.entities.Customer;
import com.sid.gl.stub.CustomerServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer from(CustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        return customer;
    }

    public CustomerResponse fromCustom(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }

    public CustomerServiceOuterClass.Customer fromCustomerResponse(CustomerResponse response){
        CustomerServiceOuterClass.Customer customer1 = CustomerServiceOuterClass.Customer.newBuilder()
                .setId(response.id())
                .setName(response.name())
                .setEmail(response.email())
                .build();
        return customer1;
    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        CustomerServiceOuterClass.Customer customer1 = CustomerServiceOuterClass.Customer.newBuilder()
                .setId(customer.getId())
                .setName(customer.getName())
                .setEmail(customer.getEmail())
                .build();
        return customer1;
    }

    public Customer fromGrpcCustomer(CustomerServiceOuterClass.CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .build();
        return customer;

    }


}
