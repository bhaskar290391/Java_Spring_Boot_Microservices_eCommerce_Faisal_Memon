package com.app.ecom.service;

import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceIMPL implements  UserService{

    private List<User> userList= new ArrayList<>();
    private Long nextId=1L;

    @Override
    public List<User> fetchAllUsers() {
        return userList;
    }

    @Override
    public void addUsers(User user) {
        user.setId(nextId++);
        userList.add(user);
    }
}
