package com.example.BankingApp.service;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.DepositModel;
import com.example.BankingApp.model.WithdrawModel;

public interface WithdrawService {
    public WithdrawModel createWithdraw(WithdrawModel withdrawModel);
    ApiResponse getWithdrawPagination(Long accountNo, int page, int size,
                                      String sortCol, String sortType);
}
