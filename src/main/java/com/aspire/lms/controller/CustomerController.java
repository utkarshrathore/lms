package com.aspire.lms.controller;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {
    @Autowired
    private UserService customerService;

    @PostMapping("")
    public User createCustomer(@RequestBody User customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("")
    public List<User> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public User getCustomerById(
            @PathVariable(value = "id") Long customerId)
            throws EntityNotFoundException {
        return customerService.getCustomerById(customerId);
    }
}
