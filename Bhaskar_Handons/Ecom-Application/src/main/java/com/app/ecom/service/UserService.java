package com.app.ecom.service;

import com.app.ecom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> fetchAllUsers();

    public void addUsers(User user);

    public Optional<User> fetchUser(Long id);

    boolean updateUser(Long id, User updatedUser);
}
