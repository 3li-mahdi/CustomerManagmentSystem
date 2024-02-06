package com.system.managment.customer.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.system.managment.customer.entity.Customer;
import com.system.managment.customer.endpoint.dto.CustomerDto;
import com.system.managment.customer.persistence.CustomerRepository;
import javax.transaction.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SimpleCustomerServiceTest {

    @Autowired
    private SimpleCustomerService simpleCustomerService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void beforeEach() {
       customerRepository.deleteAll();
    }

    
    @Test
    public void createCustomer_findCustomerById_returnsCustomerWithGivingId() {

        final CustomerDto customer = new CustomerDto();
        customer.setName("Franz");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Ali@gmail.com");
        
        final CustomerDto createdCustomerDto = simpleCustomerService.createCustomer(customer);

        final CustomerDto returnsCustomerById = simpleCustomerService.findCustomerById(createdCustomerDto.getId());
        assertNotNull(returnsCustomerById);
        assertThat(returnsCustomerById.getId()).isNotNull();
        assertEquals(createdCustomerDto.getId(), returnsCustomerById.getId());
    }

    @Test
    public void newCustomer_createCustomerCalled_CustomerIsCreated() {

        final CustomerDto customer = new CustomerDto();
        customer.setName("Test2");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Ali@gmail.com");

        final CustomerDto createdCustomerDto = simpleCustomerService.createCustomer(customer);
        assertNotNull(createdCustomerDto);
        assertThat(createdCustomerDto.getId()).isNotNull();
        assertEquals("Test2", createdCustomerDto.getName());
    }

    @Test
    public void customerExists_findAllCustomers_returnsAllCustomers() {

        final Customer firstCustomer = new Customer();
        firstCustomer.setName("Test2");
        firstCustomer.setAddress("Baghdad");
        firstCustomer.setPhoneNum("07706026375");
        firstCustomer.setEmail("Test2@gmail.com");
        customerRepository.save(firstCustomer);

        final Customer secondCustomer = new Customer();
        secondCustomer.setName("Test3");
        secondCustomer.setAddress("Baghdad");
        secondCustomer.setPhoneNum("07706026375");
        secondCustomer.setEmail("Test3@gmail.com");
        customerRepository.save(secondCustomer);

        List<CustomerDto> allCustomer = simpleCustomerService.getAllCustomers();
        assertThat(allCustomer).hasSize(2);
    }

    @Test
    public void newCustomer_deleteCustomerCalled_CustomerIsDeleted() {
        final Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Test@gmail.com");
        customerRepository.save(customer);
        simpleCustomerService.deleteCustomer(customer.getId());

        assertThat(customerRepository.findAll()).hasSize(0);
    }
    
    @Test
    public void whenDeleteAllCustomersFromRepository_thenRepositoryShouldBeEmpty() {
    	
        final Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Test@gmail.com");
        customerRepository.save(customer);

        customerRepository.deleteAll();

        assertThat(customerRepository.count()).isEqualTo(0);
    }

    @Test
    public void newCustomer_updateCustomerCalled_customerIsUpdated() {

        Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Test@gmail.com");

        customerRepository.save(customer);

        final CustomerDto customerToBeUpdated = new CustomerDto();
        customerToBeUpdated.setName("Test1");

        CustomerDto updatedCustomer = simpleCustomerService.updateCustomer(customer.getId(), customerToBeUpdated);

        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getId()).isEqualTo(customer.getId());
        assertThat(updatedCustomer.getName()).isEqualTo(customerToBeUpdated.getName());
    }
}