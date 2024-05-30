package com.example.BankingApp.repository;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit,Long> {
    Page<Deposit> findAllByAccounts_AccountNo(Long accountNo,Pageable pageable);
}
