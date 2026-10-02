package com.app.ecom.service;

import com.app.ecom.dao.UserRepository;
import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements  UserService{

    private final UserRepository userRepository;

    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUsers(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> fetchUser(Long id) {

        //Using For Loop
       /*

        for (User user : userList){
            if(user.getId().equals(id)){
                return  user;
            }
        }


        */
        return  userRepository.findById(id);
    }

    @Override
    public boolean updateUser(Long id, User updatedUser) {

        return userRepository.findById(id).map(existingUser ->{
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
}
