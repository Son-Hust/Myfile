package com.admin.crud.controller;

import com.admin.crud.exception.NotFoundException;
import com.admin.crud.model.User;
import com.admin.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    //Get all User
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //Creat new User
    @PostMapping("/users")
    public User creatUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    //Get User by Id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new NotFoundException("User with id " + userId + " not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Update User by Id
    @PutMapping("/users/{id}")
    public ResponseEntity<?> UpdateUserById(@PathVariable(value = "id") Long userId,@Valid @RequestBody User userDetail){
        User user=userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User with id " + userId + " not found"));
        user.setName(userDetail.getName());
        user.setAddress(userDetail.getAddress());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete User by Id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long userId){
        User user=userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User with id " + userId + " not found"));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
