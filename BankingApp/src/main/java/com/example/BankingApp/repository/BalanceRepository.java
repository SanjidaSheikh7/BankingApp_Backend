package com.example.BankingApp.repository;

import com.example.BankingApp.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long> {
    Balance findByAccounts_Id(Long accountId);
}
