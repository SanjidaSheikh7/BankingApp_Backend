package com.example.BankingApp.model;


import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.util.ConvertDate;
import lombok.*;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountsModel {
    private Long id;
    private String name;
    private String father_name;
    private String mother_name;
    private String phoneNo;
    private String email;
    private String dob;
    private String address;
    private GenderModel genderModel;
    private EducationModel educationModel;
    private AccountTypeModel accountTypeModel;
    private Long genderId;
    private Long educationId;
    private Long accountTypeId;


    public AccountsModel SetAccountModel(Accounts accounts,
                                      EducationModel educationModel,
                                      GenderModel genderModel,
                                      AccountTypeModel accountTypeModel){
        this.setId(accounts.getId());
        this.setName(accounts.getName());
        this.setFather_name(accounts.getFather_name());
        this.setMother_name(accounts.getMother_name());
        this.setPhoneNo(accounts.getPhoneNo());
        this.setEmail(accounts.getEmail());
        this.setDob(ConvertDate.dateToString(accounts.getDob(),ConvertDate.DD_MM_YYYY));
        this.setAddress(accounts.getAddress());
        this.setGenderModel(genderModel);
        this.setEducationModel(educationModel);
        this.setAccountTypeModel(accountTypeModel);
        this.setGenderId(genderModel.getId());
        this.setEducationId(educationModel.getId());
        this.setAccountTypeId(accountTypeModel.getId());
        return this;
    }
}
