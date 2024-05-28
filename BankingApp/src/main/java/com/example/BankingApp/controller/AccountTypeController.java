package com.example.BankingApp.controller;

import com.example.BankingApp.model.AccountTypeModel;
import com.example.BankingApp.service.AccountTypeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accountType/v1/")
public class AccountTypeController {
    @Autowired
    private final AccountTypeService accountTypeService;
    @GetMapping("/list")
    public ResponseEntity<List<AccountTypeModel>> getAllAccountTypeList(){
        List<AccountTypeModel> accountTypeModelList=accountTypeService
                .getAllAccountTypeList();
        return new ResponseEntity<>(accountTypeModelList, HttpStatus.OK);

    }

    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException{
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment;filename=accountType.xls";
        httpServletResponse.setHeader(headerKey,headerValue);
        accountTypeService.generateExcel(httpServletResponse);
    }
}
