package com.dev.myblog.repository;

import com.dev.myblog.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository {
    private static final List<User> DATABASE = new ArrayList<>();

    static {
        DATABASE.add(new User(1,"Bang", "Vo Anh","vab@example.com"));
        DATABASE.add(new User(2,"Ha", "Tran Khanh","tkh@example.com"));
        DATABASE.add(new User(3,"Test", "Nguyen Van","abc@example.com"));
    };


    public User addUser(User user){
        DATABASE.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        return List.copyOf(DATABASE);
    }

    public User getUserById(Integer id){
        return DATABASE.stream().filter(user ->id.equals(user.getId())).findFirst().orElseThrow();
    }

    public void updateUser(User user){

    }

    public Boolean deleteById(Integer id){
        User userDelete = DATABASE.stream().filter(user -> id.equals(user.getId())).findFirst().orElseThrow();
        DATABASE.remove(userDelete);
        return Boolean.TRUE;
    }
}
