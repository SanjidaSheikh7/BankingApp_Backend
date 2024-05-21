package com.example.BankingApp.service;

import com.example.BankingApp.entity.Education;
import com.example.BankingApp.model.EducationModel;
import com.example.BankingApp.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    @Override
    public EducationModel getAllEducationList() {
        List<Education> educationList=educationRepository.findAll();
        return null;
    }
}
