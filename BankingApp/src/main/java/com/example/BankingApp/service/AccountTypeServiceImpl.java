package com.example.BankingApp.service;

import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.AccountTypeModel;
import com.example.BankingApp.repository.AccountTypeRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {
        List<AccountType> accountTypeList=accountTypeRepository.findAll();
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("AccountType Info");
        HSSFRow row=sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("type");
        row.createCell(2).setCellValue("status");

        int index=1;
        for(AccountType accountType:accountTypeList){
            HSSFRow dataRow=sheet.createRow(index);
            dataRow.createCell(0).setCellValue(accountType.getId());
            dataRow.createCell(1).setCellValue(accountType.getType());
            dataRow.createCell(2).setCellValue(accountType.isStatus());
            index++;
        }
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
