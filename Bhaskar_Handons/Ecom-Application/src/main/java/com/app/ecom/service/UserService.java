package com.app.ecom.service;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserResponse> fetchAllUsers();

    public Optional<UserResponse> fetchUser(Long id);

    public void addUsers(UserRequest user);

    boolean updateUser(Long id, UserRequest updatedUser);




}
