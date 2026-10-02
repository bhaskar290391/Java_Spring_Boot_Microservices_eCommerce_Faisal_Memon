package com.app.ecom.service;

import com.app.ecom.entity.User;

import java.util.List;

public interface UserService {

    public List<User> fetchAllUsers();

    public void addUsers(User user);

    public User fetchUser(Long id);
}
