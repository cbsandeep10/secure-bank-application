package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.AddUser;
import com.example.banking.bank_app.model.Auth_user;
import com.example.banking.bank_app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public Page<User> getPaginated(Pageable pageable);

    public Long findUserByEmail(String email);

    public Long findUserByPhone(String phone);

    public void saveUser (Auth_user user);

    public boolean userAlreadyExist (Auth_user user);

    public List<Auth_user> getAllUsers();

    public User getUserByUserId(Long userId);

    public void saveOrUpdate(Auth_user user);

    public void saveNewUser(User user);

    public void deleteUser(Long userId);

}
