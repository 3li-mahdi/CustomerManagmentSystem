package com.system.managment.customer.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.system.managment.customer.entity.Customer;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void beforeEach() {
    	customerRepository.deleteAll();
    }

    @Test
    public void newCustomer_customerIsSaved_customerWasSaved() {
        Customer customer = new Customer();
        customer.setName("Locus");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Ali@gmail.com");

        Customer savedCustomer = customerRepository.save(customer);

        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getCreatedAt()).isNotNull();
        assertThat(customerRepository.findById(savedCustomer.getId())).isNotNull();

    }
}