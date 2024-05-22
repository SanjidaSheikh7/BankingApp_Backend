package com.example.BankingApp.controller;

import com.example.BankingApp.model.EducationModel;
import com.example.BankingApp.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/education/v1/")
public class EducationController {
    @Autowired
    private final EducationService educationService;

    @GetMapping("/list")
    public ResponseEntity<List<EducationModel>> getAllEducationList(){
        List<EducationModel> educationModelList=educationService.getAllEducationList();
        return new ResponseEntity<>(educationModelList, HttpStatus.OK);
    }
}
