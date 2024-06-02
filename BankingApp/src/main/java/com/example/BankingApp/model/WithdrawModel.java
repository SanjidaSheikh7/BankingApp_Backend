package com.example.BankingApp.model;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Withdraw;
import lombok.*;

import java.util.Calendar;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WithdrawModel {
    private Long id;
    private Double withdrawAmount;
    private String withdrawTransactionId;
    private Calendar withdrawTime;
    private AccountsModel accountsModel;
    private Long accountNo;
    public WithdrawModel SetWithdrawModel(Withdraw withdraw, AccountsModel accountsModel){
        this.setId(withdraw.getId());
        this.setWithdrawAmount(withdraw.getWithdrawAmount());
        this.setWithdrawTransactionId(UUID.randomUUID().toString());
        Calendar calendar = Calendar.getInstance();
        this.setWithdrawTime(calendar);
        this.setAccountsModel(accountsModel);
        this.setAccountNo(accountsModel.getAccountNo());
        return this;
    }
}
