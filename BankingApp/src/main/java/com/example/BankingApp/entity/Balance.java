package com.example.BankingApp.entity;

import com.example.BankingApp.model.BalanceModel;
import com.example.BankingApp.model.DepositModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double currentBalance;
    @OneToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Accounts accounts;

    public Balance SetBalance(Accounts accounts){
        this.setCurrentBalance(accounts.getIntialAmount());
        this.setAccounts(accounts);
        return this;
    }
}
