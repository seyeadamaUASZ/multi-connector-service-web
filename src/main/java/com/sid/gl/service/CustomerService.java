package com.sid.gl.service;

import com.sid.gl.dto.CustomerRequest;
import com.sid.gl.dto.CustomerResponse;
import com.sid.gl.entities.Customer;
import com.sid.gl.mapper.CustomerMapper;
import com.sid.gl.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest){
        return customerMapper.fromCustom(customerRepository.save(customerMapper.from(customerRequest)));
    }

    public List<CustomerResponse> findAll(){
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustom)
                .collect(Collectors.toList());
    }

    public CustomerResponse findCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("customer with %s not found ",id)));
        return customerMapper.fromCustom(customer);
    }
}
