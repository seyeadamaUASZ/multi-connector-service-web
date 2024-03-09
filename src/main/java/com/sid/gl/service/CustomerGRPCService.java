package com.sid.gl.service;

import com.sid.gl.dto.CustomerResponse;
import com.sid.gl.entities.Customer;
import com.sid.gl.mapper.CustomerMapper;
import com.sid.gl.repository.CustomerRepository;
import com.sid.gl.stub.CustomerServiceGrpc;
import com.sid.gl.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGRPCService extends
        CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;
    @Override
    public void getAllCustomer(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<CustomerResponse> customerList=customerService.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers=
                customerList.stream()
                        .map(customerMapper::fromCustomerResponse)
                        .collect(Collectors.toList());

        CustomerServiceOuterClass.GetCustomersResponse getCustomersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(grpcCustomers)
                        .build();
       responseObserver.onNext(getCustomersResponse);
       responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        CustomerResponse customerResponse = customerService.findCustomerById(request.getCustomerId());
        CustomerServiceOuterClass.GetCustomerByIdResponse response =
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomerResponse(customerResponse))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
       Customer customer = customerMapper.fromGrpcCustomer(request.getCustomer());
       Customer customer1= customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response=
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customer1))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
