package com.example.BankingApp.entity;


import com.example.BankingApp.model.AccountModel;
import com.example.BankingApp.model.EducationModel;
import com.example.BankingApp.model.GenderModel;
import com.example.BankingApp.model.UserModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="father_name")
    private String fName;
    @Column(name="mother_name")
    private String mName;
    private String phoneNo;
    private String email;
    private Date dob;
    private String address;


    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="gender_id",referencedColumnName = "id")
    private Gender gender;


    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="eduType_id",referencedColumnName = "id")
    private Education education;


    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="accountType_id",referencedColumnName = "id")
    private AccountType accountType;


    public User SetUser(UserModel userModel,
                        Education education,
                        Gender gender,
                        AccountType accountType){
        this.setId(userModel.getId());
        this.setName(userModel.getName());
        this.setFName(userModel.getFName());
        this.setMName(userModel.getMName());
        this.setPhoneNo(userModel.getPhoneNo());
        this.setEmail(userModel.getEmail());
        this.setDob(userModel.getDob());
        this.setAddress(userModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        return this;
    }


    public User UpdateUser(UserModel userModel,
                           Education education,
                           Gender gender,
                           AccountType accountType){
        this.setName(userModel.getName());
        this.setFName(userModel.getFName());
        this.setMName(userModel.getMName());
        this.setPhoneNo(userModel.getPhoneNo());
        this.setEmail(userModel.getEmail());
        this.setDob(userModel.getDob());
        this.setAddress(userModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        return this;
    }


}

