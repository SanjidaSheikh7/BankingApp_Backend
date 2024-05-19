package com.example.BankingApp.controller;

import com.example.BankingApp.model.UserModel;
import com.example.BankingApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user/v1/")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping(path ="/create" )
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
        UserModel newUser=userService.createUser(userModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id,
                                                @RequestBody UserModel userModel){
        UserModel updateUser=userService.updateUser(id,userModel);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
