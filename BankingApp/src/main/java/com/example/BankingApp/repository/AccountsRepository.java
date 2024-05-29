package com.example.BankingApp.repository;


import com.example.BankingApp.entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Page<Accounts> findAllByNameContainsIgnoreCase(String accountName, Pageable pageable);
}
