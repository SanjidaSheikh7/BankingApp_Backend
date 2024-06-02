package com.example.BankingApp.controller;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.DepositModel;
import com.example.BankingApp.model.WithdrawModel;
import com.example.BankingApp.service.DepositService;
import com.example.BankingApp.service.WithdrawService;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/withdraw/v1/")
public class WithdrawController {
    @Autowired
    private final WithdrawService withdrawService;
    @PostMapping(path ="/create" )
    public ResponseEntity<WithdrawModel> createWithdraw(@RequestBody WithdrawModel withdrawModel){
        WithdrawModel withdraw= withdrawService.createWithdraw(withdrawModel);
        return new ResponseEntity<>(withdraw, HttpStatus.CREATED);
    }
    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getWithdrawPagination(@Nullable @RequestParam("accountNo") Long accountNo,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "id") String sortCol,
                                                            @RequestParam(defaultValue = "ASC") String sortType) {
        ApiResponse withdrawModels = withdrawService.getWithdrawPagination(accountNo,page,size,sortCol,sortType);
        return new ResponseEntity<>(withdrawModels, HttpStatus.OK);
    }
}
