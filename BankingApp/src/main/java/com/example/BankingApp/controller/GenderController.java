package com.example.BankingApp.controller;

import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.service.GenderService;
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
@RequestMapping(path = "/gender/v1/")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/list")
    public ResponseEntity<List<GenderModel>> getAllGenderList(){
        List<GenderModel> genderModelList=genderService.getAllGenderList();
        return new ResponseEntity<>(genderModelList, HttpStatus.OK);
    }
}
