package com.example.BankingApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name="bank_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private boolean status;
}
