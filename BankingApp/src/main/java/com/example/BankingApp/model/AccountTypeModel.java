package com.example.BankingApp.model;


import com.example.BankingApp.entity.AccountType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountTypeModel {
    private Long id;
    private String accountType;
    private boolean status;

    public AccountTypeModel SetAccountModel(AccountType accountType){
        this.setId(accountType.getId());
        this.setAccountType(accountType.getType());
        this.setStatus(accountType.isStatus());
        return this;
    }
}
