package com.example.BankingApp.service;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.model.BalanceModel;
import com.example.BankingApp.model.DepositModel;

public interface BalanceService {
    public BalanceModel createBalance(Accounts accounts);
    public void updateCurrentBalance(String balanceType,Double amount,Accounts account);
    public BalanceModel getByAccountId(long id);
}
