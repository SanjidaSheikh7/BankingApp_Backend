package com.example.BankingApp.service;

import com.example.BankingApp.model.AccountTypeModel;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface AccountTypeService {
    public List<AccountTypeModel> getAllAccountTypeList();

    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException ;
}
