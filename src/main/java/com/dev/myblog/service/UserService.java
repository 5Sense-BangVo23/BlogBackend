package com.dev.myblog.service;

import com.dev.myblog.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    void updateUser(User user);

    Boolean deleteById(Integer id);

}
