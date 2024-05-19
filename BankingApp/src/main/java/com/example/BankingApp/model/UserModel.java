package com.example.BankingApp.model;


import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.entity.Education;
import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.entity.User;
import jakarta.persistence.Column;
import lombok.*;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    private Long id;
    private String name;
    private String fName;
    private String mName;
    private String phoneNo;
    private String email;
    private Date dob;
    private String address;
    private GenderModel genderModel;
    private EducationModel educationModel;
    private AccountModel accountModel;
    private Long genderId;
    private Long educationId;
    private Long accountID;


    public UserModel SetUserModel(User user,
                                  EducationModel educationModel,
                                  GenderModel genderModel,
                                  AccountModel accountModel){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setFName(user.getFName());
        this.setMName(user.getMName());
        this.setPhoneNo(user.getPhoneNo());
        this.setEmail(user.getEmail());
        this.setDob(user.getDob());
        this.setAddress(user.getAddress());
        this.setGenderModel(genderModel);
        this.setEducationModel(educationModel);
        this.setAccountModel(accountModel);
        this.setGenderId(genderModel.getId());
        this.setEducationId(educationModel.getId());
        this.setAccountID(accountModel.getId());
        return this;
    }
}
