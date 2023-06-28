package com.aspire.lms.services.impl;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;
import com.aspire.lms.repository.UserRepository;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements UserService {

    @Autowired
    private UserRepository customerRepository;


    @Override
    public User createCustomer(User customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<User> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public User getCustomerById(Long id) throws EntityNotFoundException {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No customer found with id: "+ id)
        );
    }
}
