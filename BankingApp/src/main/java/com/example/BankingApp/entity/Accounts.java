package com.example.BankingApp.entity;


import com.example.BankingApp.model.AccountsModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @Column(name="father_name")
    private String father_name;
//    @Column(name="mother_name")
    private String mother_name;
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


    public Accounts SetUser(AccountsModel accountsModel,
                            Education education,
                            Gender gender,
                            AccountType accountType){
        this.setId(accountsModel.getId());
        this.setName(accountsModel.getName());
        this.setFather_name(accountsModel.getFather_name());
        this.setMother_name(accountsModel.getMother_name());
        this.setPhoneNo(accountsModel.getPhoneNo());
        this.setEmail(accountsModel.getEmail());
        this.setDob(accountsModel.getDob());
        this.setAddress(accountsModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        return this;
    }


    public Accounts UpdateUser(AccountsModel accountsModel,
                               Education education,
                               Gender gender,
                               AccountType accountType){
        this.setName(accountsModel.getName());
        this.setFather_name(accountsModel.getFather_name());
        this.setMother_name(accountsModel.getMother_name());
        this.setPhoneNo(accountsModel.getPhoneNo());
        this.setEmail(accountsModel.getEmail());
        this.setDob(accountsModel.getDob());
        this.setAddress(accountsModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        return this;
    }


}

