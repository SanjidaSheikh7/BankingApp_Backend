package com.example.BankingApp.service;

import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService{
    private final GenderRepository genderRepository;
    @Override
    public List<GenderModel> getAllGenderList() {
        List<Gender> genderList=genderRepository.findAll();
        List<GenderModel> genderModelList=new ArrayList<>();
        if(!genderList.isEmpty()){
            genderModelList=genderList.stream()
                    .map(gender->new GenderModel().SetGenderModel(gender)).toList();
        }else {
            throw new NotFoundException("No data in the database");
        }
        return genderModelList;
    }
}
