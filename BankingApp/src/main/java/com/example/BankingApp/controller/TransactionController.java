package com.example.BankingApp.controller;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.TransactionModel;
import com.example.BankingApp.service.TransactionService;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/transaction/v1/")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;
//    @GetMapping("/list")
//    public ResponseEntity<List<TransactionModel>> getAllTeacherList(@Nullable @RequestParam(value="transactionType",defaultValue = "") String transactionType) {
//        List<TransactionModel> transactionModelList = transactionService.getAllTransaction(transactionType);
//        return new ResponseEntity<>(transactionModelList, HttpStatus.OK);
//    }
@GetMapping("/list")
public ResponseEntity<ApiResponse> getAllTransaction(@Nullable @RequestParam(value="transactionType",defaultValue = "") String transactionType,
                                                     @Nullable @RequestParam("accountNo") Long accountNo,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortCol,
                                                     @RequestParam(defaultValue = "ASC") String sortType) {
    ApiResponse transactionModelList = transactionService.getAllTransaction(transactionType,accountNo,page,size,sortCol,sortType);
    return new ResponseEntity<>(transactionModelList, HttpStatus.OK);
}
}
