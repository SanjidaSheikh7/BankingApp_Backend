package com.example.BankingApp.service;

import com.example.BankingApp.entity.Chart;
import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.ChartModel;
import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.repository.ChartRepository;
import com.example.BankingApp.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final ChartRepository chartRepository;
    @Override
    public List<ChartModel> getAllChartList() {
        List<Chart> chartList=chartRepository.findAll();
        List<ChartModel> chartModelList=new ArrayList<>();
        if(!chartList.isEmpty()){
            chartModelList=chartList.stream()
                    .map(chart->new ChartModel().SetChartModel(chart)).toList();
        }else {
            throw new NotFoundException("No data in the database");
        }
        return chartModelList;
    }
}
