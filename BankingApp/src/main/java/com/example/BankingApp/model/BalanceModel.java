package com.example.BankingApp.model;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Balance;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BalanceModel {
    private Long id;
    private Double currentBalance;
    private AccountsModel accountsModel;
    private long accountId;
    public BalanceModel SetBalanceModel(Balance balance, AccountsModel accountsModel){
        this.setId(balance.getId());
        this.setCurrentBalance(balance.getCurrentBalance());
        this.setAccountsModel(accountsModel);
        this.setAccountId(accountsModel.getId());
        return this;
    }
}
