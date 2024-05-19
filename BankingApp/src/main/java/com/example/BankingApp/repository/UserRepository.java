package com.example.BankingApp.repository;


import com.example.BankingApp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAllByNameContainsIgnoreCase(String userName, Pageable pageable);

}
