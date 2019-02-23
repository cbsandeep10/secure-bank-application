package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserByUserId(Long userId);

    public void saveOrUpdate(User user);

    public void deleteUser(Long userId);

}
