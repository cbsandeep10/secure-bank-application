package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.AddUser;
import com.example.banking.bank_app.model.Auth_role;
import com.example.banking.bank_app.model.Auth_user;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.respository.AuthUserRepository;
import com.example.banking.bank_app.respository.RoleRepository;
import com.example.banking.bank_app.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public Page<User> getPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Long findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Long findUserByPhone(String phone){
        return userRepository.findUserByPhone(phone);
    }

    @Override
    public List<Auth_user> getAllUsers() {
        return (List<Auth_user>) authUserRepository.findAll();
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void saveOrUpdate(Auth_user user) {
        authUserRepository.save(user);
    }

    @Override
    public void saveNewUser(User user) {
        userRepository.save(user);
    }


    @Override
    public void deleteUser(Long userId) {
        authUserRepository.deleteById(userId);
    }

    @Override
    public void saveUser (Auth_user user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus ("VERIFIED");
        Auth_role userRole = roleRepository.findByRole("xxxxxx");    // SITE_USER or what not
        user.setRoles (new HashSet<Auth_role>(Arrays.asList(userRole)));
        authUserRepository.save(user);
    }


    @Override
    public boolean userAlreadyExist (Auth_user user) {

        // no sol......
        return false;
    }
}