package com.example.demo.service;

import com.example.demo.entities.User;

public interface UserService {
    void save(User user);
    User findById(int id);
}
