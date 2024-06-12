package com.example.BankingApp.model;

import com.example.BankingApp.entity.Chart;
import com.example.BankingApp.entity.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChartModel {
    private Long id;
    private String month;
    private Long cost;
    private boolean status;


    public ChartModel SetChartModel (Chart chart ){
        this.setId(chart.getId());
        this.setMonth(chart.getMonth());
        this.setCost(chart.getCost());
        this.setStatus(chart.isStatus());
        return this;
    }
}
