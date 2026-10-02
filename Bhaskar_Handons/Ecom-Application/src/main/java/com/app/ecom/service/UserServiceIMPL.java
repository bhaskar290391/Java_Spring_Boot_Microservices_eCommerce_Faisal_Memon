package com.app.ecom.service;

import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return  userList.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean updateUser(Long id, User updatedUser) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().map(existingUser ->{

            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            return true;
        }).orElse(false);
    }
}
