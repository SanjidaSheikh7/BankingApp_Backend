package com.example.BankingApp.controller;

import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.model.BalanceModel;
import com.example.BankingApp.service.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/balance/v1/")
public class BalanceController {
    @Autowired
    private final BalanceService balanceService;
    @GetMapping("/find/{id}")
    public ResponseEntity<BalanceModel> getByAccountId(@PathVariable Long id){
        BalanceModel balanceModel = balanceService.getByAccountId(id);
        return new ResponseEntity<>(balanceModel, HttpStatus.OK);
    }
}
