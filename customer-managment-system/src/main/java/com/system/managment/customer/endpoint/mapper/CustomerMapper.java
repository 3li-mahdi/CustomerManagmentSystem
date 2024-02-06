package com.system.managment.customer.endpoint.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.system.managment.customer.endpoint.dto.CustomerDto;
import com.system.managment.customer.entity.Customer;

@Component
public class CustomerMapper {


    public Customer mapFromDtoToEntity (CustomerDto dto) {
    	if (dto == null) {
    		return null;
    	}
    	Instant createdAt = dto.getCreatedAt() != null ? dto.getCreatedAt() : null;
        return new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getPhoneNum(), dto.getEmail(), createdAt, dto.getUpdatedAt());

    }

    public CustomerDto mapFromEntityToDto(Customer entity) {
        if (entity == null) {
            return null;
        }
        return new CustomerDto(entity.getId(), entity.getName(), entity.getAddress(), entity.getPhoneNum(), entity.getEmail(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

}
