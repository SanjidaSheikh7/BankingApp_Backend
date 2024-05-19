package com.example.BankingApp.model;


import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.entity.Education;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountModel {
    private Long id;
    private String accountType;
    private boolean status;

    public AccountModel SetAccountModel(AccountType accountType){
        this.setId(accountType.getId());
        this.setAccountType(accountType.getType());
        this.setStatus(accountType.isStatus());
        return this;
    }
}
