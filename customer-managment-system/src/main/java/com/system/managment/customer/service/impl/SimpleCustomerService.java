package com.system.managment.customer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.system.managment.customer.endpoint.dto.CustomerDto;
import com.system.managment.customer.endpoint.mapper.CustomerMapper;
import com.system.managment.customer.entity.Customer;
import com.system.managment.customer.exception.NotFoundException;
import com.system.managment.customer.persistence.CustomerRepository;
import com.system.managment.customer.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCustomerService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public SimpleCustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer foundCustomer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));
        return customerMapper.mapFromEntityToDto(foundCustomer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> AllCustomers = this.customerRepository.findAll();
        return AllCustomers.stream()
            .map(this.customerMapper::mapFromEntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        LOGGER.info("creating new customer with id={}", customerDto.getId());
        Customer customerEntity = this.customerMapper.mapFromDtoToEntity(customerDto);
        Customer saveCustomerEntity = this.customerRepository.save(customerEntity);
        LOGGER.info("saving entity");

        return this.customerMapper.mapFromEntityToDto(saveCustomerEntity);
    }

    @Override
    public void deleteCustomer(Long id) {
        LOGGER.info("deleting customer with id={}", id);
        customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id: " + id + " does not exist in the database"));
        customerRepository.deleteById(id);
    
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {

        LOGGER.info("updating customer with id={}", customerDto.getId());
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer id{} : " + id));
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setAddress(customerDto.getAddress());
        existingCustomer.setPhoneNum(customerDto.getPhoneNum());
        existingCustomer.setEmail(customerDto.getEmail());
        
        Customer customerToBeUpdate = this.customerRepository.save(existingCustomer);
        LOGGER.info("customer is updated");
        return this.customerMapper.mapFromEntityToDto(customerToBeUpdate);
    }
}
