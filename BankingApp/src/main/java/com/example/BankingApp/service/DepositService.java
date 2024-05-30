package com.example.BankingApp.service;

import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.DepositModel;

public interface DepositService {
    public DepositModel createDeposit(DepositModel depositModel);
    ApiResponse getDepositPagination(Long accountNo, int page, int size, String sortCol, String sortType);
}
