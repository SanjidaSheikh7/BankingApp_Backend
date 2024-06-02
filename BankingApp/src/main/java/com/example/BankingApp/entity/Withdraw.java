package com.example.BankingApp.entity;

import com.example.BankingApp.model.DepositModel;
import com.example.BankingApp.model.WithdrawModel;
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
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double withdrawAmount;
    private String withdrawTransactionId;
    private Calendar withdrawTime;
    //    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "accountNo", referencedColumnName = "accountNo")
    private Accounts accounts;

    public Withdraw SetWithdraw(WithdrawModel withdrawModel, Accounts accounts){
        this.setId(withdrawModel.getId());
        this.setWithdrawAmount(withdrawModel.getWithdrawAmount());
        this.setWithdrawTransactionId(UUID.randomUUID().toString());
        Calendar calendar = Calendar.getInstance();
        this.setWithdrawTime(calendar);
        this.setAccounts(accounts);
        return this;
    }
}
