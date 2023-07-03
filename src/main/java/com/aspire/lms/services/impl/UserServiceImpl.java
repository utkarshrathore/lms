package com.aspire.lms.services.impl;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;
import com.aspire.lms.repository.UserRepository;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No user found with id: "+ id)
        );
    }
}
