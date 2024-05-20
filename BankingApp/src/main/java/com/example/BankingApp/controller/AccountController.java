package com.example.BankingApp.controller;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.service.AccountService;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/account/v1/")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getAccountPagination(@Nullable @RequestParam("userName") String accountName,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "id") String sortCol,
                                                            @RequestParam(defaultValue = "ASC") String sortType) {
        ApiResponse userModels = accountService.getAccountPagination(accountName,page,size,sortCol,sortType);
        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AccountsModel> getAccountById(@PathVariable Long id){
        AccountsModel accountsModel = accountService.getAccountById(id);
        return new ResponseEntity<>(accountsModel,HttpStatus.OK);
    }

    @PostMapping(path ="/create" )
    public ResponseEntity<AccountsModel> createAccount(@RequestBody AccountsModel accountsModel){
        AccountsModel newUser= accountService.createAccount(accountsModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<AccountsModel> updateAccount(@PathVariable Long id,
                                                    @RequestBody AccountsModel accountsModel){
        AccountsModel updateUser= accountService.updateAccount(id, accountsModel);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
