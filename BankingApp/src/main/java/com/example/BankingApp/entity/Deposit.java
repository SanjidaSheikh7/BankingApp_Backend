package com.example.BankingApp.entity;

import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.model.DepositModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String transactionId;
    private Calendar depositTime;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "accountNo", referencedColumnName = "accountNo")
    private Accounts accounts;

    public Deposit SetDeposit(DepositModel depositModel, Accounts accounts){
        this.setAmount(depositModel.getAmount());
        this.setTransactionId(UUID.randomUUID().toString());
        Calendar calendar = Calendar.getInstance();
        this.setDepositTime(calendar);
        this.setAccounts(accounts);
        return this;
    }
}
