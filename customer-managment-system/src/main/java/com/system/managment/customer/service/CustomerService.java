package com.system.managment.customer.service;

import java.util.List;

import com.system.managment.customer.endpoint.dto.CustomerDto;

public interface CustomerService {

    /**
     * @param id of the customer to find.
     * @return the customer with the specified id.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the customer could not be found in the system.
     */
    CustomerDto findCustomerById(Long id);

    /**
     * Create the given CustomerDto.
     *
     * @param cusomerDto the new Customer to persist
     * @return the persisted customer
     */
    CustomerDto createCustomer(CustomerDto CustomerDto);

    /**
     * Find all existing customer and returns them
     *
     * @return all existing Customer
     */
    List<CustomerDto> getAllCustomers();

    /**
     * Delete the customer with the given id
     *
     * @param id of the customer to delete
     */
    void deleteCustomer(Long id);

    /**
     * Update the Customer with the given id
     *
     * @param customerDto Exciting Customer to Update
     * @return Updated Customer
     */
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

}
