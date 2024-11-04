package com.dev.myblog.service.impl;

import com.dev.myblog.model.User;
import com.dev.myblog.repository.JpaUserRepository;
import com.dev.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Qualifier(value = "jpaUserService")
public class JpaUserServiceImpl implements UserService {

    private final JpaUserRepository jpaUserRepository;
    @Override
    public User addUser(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return jpaUserRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return jpaUserRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Boolean deleteById(Integer id) {
        jpaUserRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
