package com.aspire.lms.services;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> getAllUsers();

    User getUserById(Long id) throws EntityNotFoundException;
}
