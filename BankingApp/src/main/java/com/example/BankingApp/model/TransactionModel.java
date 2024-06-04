package com.example.BankingApp.model;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.entity.Withdraw;
import com.example.BankingApp.util.ConvertDate;
import lombok.*;
import org.apache.commons.collections4.EnumerationUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionModel {
    private String transactionTime;
    private Double transactionAmount;
    private String transactionType;
    private String transactionId;
    private Long accountNo;

    public TransactionModel SetTransactionModelFromDeposit(Deposit deposit) {
        Date date=deposit.getDepositTime().getTime();
        this.setTransactionTime(ConvertDate.dateToString(date, ConvertDate.YYYY_MM_DD_time));
        this.setTransactionAmount(deposit.getAmount());
        this.setTransactionType("deposit");
        this.setTransactionId(deposit.getTransactionId());
        this.setAccountNo(deposit.getAccounts().getAccountNo());
        return this;
    }
    public TransactionModel SetTransactionModelFromWithdraw(Withdraw withdraw) {
        Date date=withdraw.getWithdrawTime().getTime();
        this.setTransactionTime(ConvertDate.dateToString(date, ConvertDate.YYYY_MM_DD_time));
        this.setTransactionAmount(withdraw.getWithdrawAmount());
        this.setTransactionType("withdraw");
        this.setTransactionId(withdraw.getWithdrawTransactionId());
        this.setAccountNo(withdraw.getAccounts().getAccountNo());
        return this;
    }
}
