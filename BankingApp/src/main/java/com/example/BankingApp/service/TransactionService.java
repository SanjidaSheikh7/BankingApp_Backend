package com.example.BankingApp.service;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.TransactionModel;

import java.util.List;

public interface TransactionService {
//    public ApiResponse getAllTransaction(String transactionType);

    ApiResponse getAllTransaction(String transactionType,Long accountNo, int page, int size,
                                  String sortCol, String sortType);
}
