package com.system.managment.customer.persistence.impl;

import org.springframework.stereotype.Repository;

import com.system.managment.customer.entity.Customer;
import com.system.managment.customer.persistence.CustomerDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaCustomerDao implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findOneById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
