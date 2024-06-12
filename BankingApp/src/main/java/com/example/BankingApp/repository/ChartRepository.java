package com.example.BankingApp.repository;

import com.example.BankingApp.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart,Long> {
}
