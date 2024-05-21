package com.example.BankingApp.service;

import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService{
    private final GenderRepository genderRepository;
    @Override
    public GenderModel getAllGenderList() {
        List<Gender> genderList=genderRepository.findAll();
        return null;
    }
}
