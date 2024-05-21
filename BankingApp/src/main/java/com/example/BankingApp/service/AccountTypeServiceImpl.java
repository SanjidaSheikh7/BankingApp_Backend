package com.example.BankingApp.service;

import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.model.AccountTypeModel;
import com.example.BankingApp.repository.AccountTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    @Override
    public AccountTypeModel getAllAccountTypeList() {
        List<AccountType> accountTypeList=accountTypeRepository.findAll();
        return null;
    }
}
