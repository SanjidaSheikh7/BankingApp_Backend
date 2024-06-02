package com.example.BankingApp.repository;

import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.entity.Withdraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw,Long> {
    Page<Withdraw> findAllByAccounts_AccountNo(Long accountNo, Pageable pageable);
}
