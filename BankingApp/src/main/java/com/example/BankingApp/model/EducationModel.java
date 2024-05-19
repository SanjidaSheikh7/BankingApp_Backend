package com.example.BankingApp.model;

import com.example.BankingApp.entity.Education;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EducationModel {
    private Long id;
    private String degree;
    private boolean status;

    public EducationModel SetEducationModel(Education education){
        this.setId(education.getId());
        this.setDegree(education.getDegree());
        this.setStatus(education.isStatus());
        return this;
    }
}
