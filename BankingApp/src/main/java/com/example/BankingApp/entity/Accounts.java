package com.example.BankingApp.entity;


import com.example.BankingApp.exception.AlreadyExistsException;
import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.util.ConvertDate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private String father_name;
    private String mother_name;
    private String phoneNo;
    private String email;
    private Date dob;
    private String address;
    private Long accountNo;
    private Double intialAmount;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="gender_id",referencedColumnName = "id")
    private Gender gender;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="eduType_id",referencedColumnName = "id")
    private Education education;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="accountType_id",referencedColumnName = "id")
    private AccountType accountType;

    public Accounts SetAccount(AccountsModel accountsModel,
                            Education education,
                            Gender gender,
                            AccountType accountType,Long accountNo){
        this.setId(accountsModel.getId());
        this.setName(accountsModel.getName());
        this.setFather_name(accountsModel.getFather_name());
        this.setMother_name(accountsModel.getMother_name());
        this.setPhoneNo(accountsModel.getPhoneNo());
        this.setEmail(accountsModel.getEmail());
        this.setDob(ConvertDate.stringToDate(accountsModel.getDob(),ConvertDate.YYYY_MM_DD));
        this.setAddress(accountsModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        this.setAccountNo(accountNo);
        this.setIntialAmount(accountsModel.getIntialAmount());
        return this;
    }

    public Long generateAccountNo(Long lastAccountNo, AccountsModel accountsModel,
                                  List<Long> accountsList){
        String lastSixDigitStr;
        String monthDigits=ConvertDate.getMonth(accountsModel.getDob());
        if (lastAccountNo == null) {
            lastSixDigitStr="000000";
        }else{
            lastAccountNo++;
            String lastAccountNoStr = lastAccountNo.toString();
            lastSixDigitStr=lastAccountNoStr.length() <= 6 ?
                    lastAccountNoStr : lastAccountNoStr.substring(lastAccountNoStr.length() - 6);
        }

        String accountNoStr="2024"+monthDigits+lastSixDigitStr;
        Long generatedAccountNo=Long.parseLong(accountNoStr);
        if(!accountsList.contains(generatedAccountNo)){
           return generatedAccountNo;
        }else{
            throw new AlreadyExistsException("Account No. already exists");
        }

    }


    public Accounts UpdateAccount(AccountsModel accountsModel,
                               Education education,
                               Gender gender,
                               AccountType accountType){
        this.setName(accountsModel.getName());
        this.setFather_name(accountsModel.getFather_name());
        this.setMother_name(accountsModel.getMother_name());
        this.setPhoneNo(accountsModel.getPhoneNo());
        this.setEmail(accountsModel.getEmail());
        this.setDob(ConvertDate.stringToDate(accountsModel.getDob(),ConvertDate.YYYY_MM_DD));
        this.setAddress(accountsModel.getAddress());
        this.setGender(gender);
        this.setEducation(education);
        this.setAccountType(accountType);
        return this;
    }

}

