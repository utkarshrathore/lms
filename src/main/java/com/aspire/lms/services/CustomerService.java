package com.aspire.lms.services;

import com.aspire.lms.exception.ResourceNotFoundException;
import com.aspire.lms.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAll();

    Customer getCustomerById(Long id) throws ResourceNotFoundException;
}
