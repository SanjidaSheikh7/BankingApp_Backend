package com.example.BankingApp.model;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Withdraw;
import com.example.BankingApp.util.ConvertDate;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
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
    private String withdrawTime;
    private AccountsModel accountsModel;
    private Long accountNo;
    private boolean success;
    public WithdrawModel SetWithdrawModel(Withdraw withdraw, AccountsModel accountsModel){
        this.success=true;
        this.setId(withdraw.getId());
        this.setWithdrawAmount(withdraw.getWithdrawAmount());
        this.setWithdrawTransactionId(UUID.randomUUID().toString());
        Date date=withdraw.getWithdrawTime().getTime(); //convert calender to date
        this.setWithdrawTime(ConvertDate.dateToString(date,ConvertDate.YYYY_MM_DD));
        this.setAccountsModel(accountsModel);
        this.setAccountNo(accountsModel.getAccountNo());
        return this;
    }
}
