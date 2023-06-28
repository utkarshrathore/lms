package com.aspire.lms.services;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;

import java.util.List;

public interface UserService {
    User createCustomer(User customer);
    List<User> getAllCustomers();

    User getCustomerById(Long id) throws EntityNotFoundException;
}
