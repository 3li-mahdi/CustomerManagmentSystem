package com.system.managment.customer.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.system.managment.customer.endpoint.dto.CustomerDto;
import com.system.managment.customer.exception.NotFoundException;
import com.system.managment.customer.service.CustomerService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CustomerEndpoint.BASE_URL)
public class CustomerEndpoint {

    static final String BASE_URL = "/customers";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEndpoint.class);
    private final CustomerService customerService;

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }
   
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
        LOGGER.info("GET {}/{}", BASE_URL, id);
        try {
        	CustomerDto foundCustomer =  customerService.findCustomerById(id);
            return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading customer", e);
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto) {
        LOGGER.info("POST {} ", BASE_URL);
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);;
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        LOGGER.info("Delete {}/{}", BASE_URL, id);

        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @PathVariable Long id, @RequestBody CustomerDto customerDto) {


        LOGGER.info("PUT {}/{}", BASE_URL, id);
        CustomerDto updatedCustomer;
        try {
            updatedCustomer = customerService.updateCustomer(id, customerDto);

        } catch (NotFoundException e) {
            LOGGER.error("NotFoundException thrown while editing a customer in REST layer: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        if (updatedCustomer == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
    }
}
