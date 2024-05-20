package com.example.BankingApp.repository;


import com.example.BankingApp.entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Page<Accounts> findAllByNameContainsIgnoreCase(String userName, Pageable pageable);

}
