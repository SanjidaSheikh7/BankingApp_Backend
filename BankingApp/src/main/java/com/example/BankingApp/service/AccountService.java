package com.example.BankingApp.service;

import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.AccountsModel;

public interface AccountService {
    public AccountsModel createAccount(AccountsModel accountsModel);
    ApiResponse getAccountPagination(String accountName, int page, int size, String sortCol, String sortType);
    public AccountsModel getAccountById(Long id);
    public AccountsModel updateAccount(Long id, AccountsModel accountsModel);
    public void deleteAccount(Long id);
}
