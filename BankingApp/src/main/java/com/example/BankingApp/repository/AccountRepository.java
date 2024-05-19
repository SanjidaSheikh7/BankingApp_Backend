package com.example.BankingApp.repository;


import com.example.BankingApp.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountType,Long> {
}
