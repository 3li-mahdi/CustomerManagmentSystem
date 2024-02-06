package com.system.managment.customer.persistence;

import com.system.managment.customer.entity.Customer;

public interface CustomerDao {

    /**
     * @param id of the customer to find.
     * @return the customer with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the customer could not be found in the database.
     */
    Customer findOneById(Long id);

}
