package com.example.BankingApp.service;

import com.example.BankingApp.entity.Education;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.EducationModel;
import com.example.BankingApp.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    @Override
    public List<EducationModel> getAllEducationList() {
        List<Education> educationList=educationRepository.findAll();
        List<EducationModel> educationModelList=new ArrayList<>();
        if(!educationList.isEmpty()){
            educationModelList=educationList.stream()
                    .map(education->new EducationModel().SetEducationModel(education)).toList();
        }else {
            throw new NotFoundException("No data in the database");
        }
        return educationModelList;
    }
}
