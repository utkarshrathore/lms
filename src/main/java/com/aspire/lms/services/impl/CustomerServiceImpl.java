package com.aspire.lms.services.impl;

import com.aspire.lms.exception.ResourceNotFoundException;
import com.aspire.lms.model.Customer;
import com.aspire.lms.repository.CustomerRepository;
import com.aspire.lms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) throws ResourceNotFoundException {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No customer found with id: "+ id)
        );
    }
}
