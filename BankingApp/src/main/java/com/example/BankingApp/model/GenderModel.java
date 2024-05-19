package com.example.BankingApp.model;

import com.example.BankingApp.entity.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenderModel {
    private Long id;
    private String name;
    private boolean status;


    public GenderModel SetGenderModel(Gender gender ){
        this.setId(gender.getId());
        this.setName(gender.getName());
        this.setStatus(gender.isStatus());
        return this;
    }
}
