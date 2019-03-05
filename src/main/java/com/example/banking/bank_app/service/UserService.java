package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserByUserId(Long userId);

    public void saveOrUpdate(User user);

    public void deleteUser(Long userId);

    public Page<User> getPaginated(Pageable pageable);

}
