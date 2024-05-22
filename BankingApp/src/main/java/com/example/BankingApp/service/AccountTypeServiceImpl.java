package com.example.BankingApp.service;

import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.AccountTypeModel;
import com.example.BankingApp.repository.AccountTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    @Override
    public List<AccountTypeModel> getAllAccountTypeList() {
        List<AccountType> accountTypeList=accountTypeRepository.findAll();
        List<AccountTypeModel> accountTypeModelList=new ArrayList<>();
        if(!accountTypeList.isEmpty()){
            accountTypeModelList=accountTypeList.stream()
                    .map(accountType -> new AccountTypeModel()
                            .SetAccountModel(accountType)).toList();
        }else {
            throw new NotFoundException("No data in the database");
        }
        return accountTypeModelList;
    }
}
