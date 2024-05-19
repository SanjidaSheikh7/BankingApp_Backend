package com.example.BankingApp.service;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.UserModel;

import java.util.List;

public interface UserService {
    public UserModel createUser(UserModel userModel);
    ApiResponse getUserPagination(String UserName, int page, int size, String sortCol, String sortType);
    public UserModel getUserById(Long id);
    public UserModel updateUser(Long id,UserModel userModel);
    public void deleteUser(Long id);
}
