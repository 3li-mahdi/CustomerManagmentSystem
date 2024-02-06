package com.system.managment.customer.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.managment.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.system.managment.customer.entity.Customer;
import com.system.managment.customer.endpoint.dto.CustomerDto;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    public void setup() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Mosul");
        customer.setPhoneNum("07706000000");
        customer.setEmail("Test@gmail.com");

        customerRepository.save(customer);
    }

    @Test
    public void findCustomerById_customerDoesntExist_returnsNotFound() throws Exception {

		this.mockMvc
            .perform(get("/customers/9897"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void newCustomer_customerIsCalled_customerIsCreates() throws Exception {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Mosul");
        customer.setPhoneNum("07706000000");
        customer.setEmail("Test@gmail.com");
        customerRepository.save(customer);

        String serializedCustomerDto = objectMapper.writeValueAsString(customer);
        this.mockMvc
            .perform
                (post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(serializedCustomerDto)
                )
            .andDo(print())
            .andExpect(status().isCreated());

    }

    @Test
    public void CustomersExists_findAllCustomers_allCustomersAreFound() throws Exception {
        this.mockMvc
            .perform(get("/customers"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Test")));
    }

    @Test
    public void newCustomer_deleteCustomerCalled_CustomerIsDeleted() throws Exception {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Mosul");
        customer.setPhoneNum("07706000000");
        customer.setEmail("Test@gmail.com");
        Customer savedCustomer = customerRepository.save(customer);

        this.mockMvc
            .perform(delete("/customers/{id}", savedCustomer.getId()))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void newCustomer_updateCustomerCalled_customerIsUpdated() throws Exception {

        Customer customer = new Customer();
        customer.setName("Test");
        customer.setAddress("Baghdad");
        customer.setPhoneNum("07706026375");
        customer.setEmail("Ali@gmail.com");
        Customer savedCustomer = customerRepository.save(customer);

        final CustomerDto customerToBeUpdated = new CustomerDto();
        customerToBeUpdated.setName("Test1");
        customerToBeUpdated.setAddress("Baghdad");
        customerToBeUpdated.setPhoneNum("07706026375");
        customerToBeUpdated.setEmail("Test1@gmail.com");

        String serializedCustomerDto = objectMapper.writeValueAsString(customerToBeUpdated);
        this.mockMvc
            .perform(put("/customers/{id}", savedCustomer.getId()).contentType(MediaType.APPLICATION_JSON).content(serializedCustomerDto))
            .andDo(print())
            .andExpect(status().isAccepted())
            .andExpect(content().string(containsString("Test1")));

    }
}