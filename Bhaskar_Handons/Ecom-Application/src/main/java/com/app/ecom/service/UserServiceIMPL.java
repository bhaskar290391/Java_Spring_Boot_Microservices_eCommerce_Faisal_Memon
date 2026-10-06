package com.app.ecom.service;

import com.app.ecom.dao.UserRepository;
import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.entity.Address;
import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements  UserService{

    private final UserRepository userRepository;

    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> fetchAllUsers() {
        List<User> userList=userRepository.findAll();

        return userList.stream()
                .map(this::mapUserToUserResponse)
                .collect(Collectors.toList());
    }



    @Override
    public void addUsers(UserRequest userRequest) {
        User user= new User();
        userRequestMappingToUserEntity(user,userRequest);
        userRepository.save(user);
    }


    @Override
    public Optional<UserResponse> fetchUser(Long id) {

        return  userRepository.findById(id).map(this::mapUserToUserResponse);
    }

    @Override
    public boolean updateUser(Long id, UserRequest updatedUser) {

        return userRepository.findById(id).map(existingUser ->{
           userRequestMappingToUserEntity(existingUser, updatedUser);
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }


    private void userRequestMappingToUserEntity(User user, UserRequest userRequest) {


        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if(userRequest.getAddress() !=null){
            Address address= new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }


    }
    private UserResponse mapUserToUserResponse(User user) {
        UserResponse response =new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());

        if(user.getAddress() !=null){
            AddressDTO address= new AddressDTO();
            address.setId(user.getId().toString());
            address.setStreet(user.getAddress().getStreet());
            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
            address.setCountry(user.getAddress().getCountry());
            address.setZipcode(user.getAddress().getZipcode());
            response.setAddress(address);
        }

        return  response;
    }
}
