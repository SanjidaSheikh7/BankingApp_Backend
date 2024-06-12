package com.example.BankingApp.controller;

import com.example.BankingApp.model.ChartModel;
import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.service.ChartService;
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
@RequestMapping(path = "/chart/v1/")
public class ChartController {
    @Autowired
    private ChartService chartService;

    @GetMapping("/list")
    public ResponseEntity<List<ChartModel>> getAllChartList(){
        List<ChartModel> chartModelList=chartService.getAllChartList();
        return new ResponseEntity<>(chartModelList, HttpStatus.OK);
    }
}
