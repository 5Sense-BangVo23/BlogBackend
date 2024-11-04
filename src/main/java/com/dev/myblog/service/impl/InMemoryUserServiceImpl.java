package com.dev.myblog.service.impl;

import com.dev.myblog.model.User;
import com.dev.myblog.repository.InMemoryUserRepository;
import com.dev.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Qualifier("inMemoryUserService")
public class InMemoryUserServiceImpl implements UserService {

    private final InMemoryUserRepository inMemoryUserRepository;
    @Override
    public User addUser(User user) {
        inMemoryUserRepository.addUser(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return inMemoryUserRepository.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return inMemoryUserRepository.getUserById(id);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Boolean deleteById(Integer id) {
        inMemoryUserRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
