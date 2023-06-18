package com.aspire.lms.controller;

import com.aspire.lms.exception.ResourceNotFoundException;
import com.aspire.lms.model.Customer;
import com.aspire.lms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public Customer createCustomer(Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("")
    public List<Customer> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(
            @PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        return customerService.getCustomerById(customerId);
    }
}
