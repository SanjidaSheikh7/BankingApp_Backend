package com.example.BankingApp.controller;
import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.DepositModel;
import com.example.BankingApp.service.DepositService;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/deposit/v1/")
public class DepositController {
    @Autowired
    private final DepositService depositService;
    @PostMapping(path ="/create" )
    public ResponseEntity<DepositModel> createDeposit(@RequestBody DepositModel depositModel){
        DepositModel deposit= depositService.createDeposit(depositModel);
        return new ResponseEntity<>(deposit, HttpStatus.CREATED);
    }
    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getDepositPagination(@Nullable @RequestParam("accountNo") Long accountNo,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "id") String sortCol,
                                                            @RequestParam(defaultValue = "ASC") String sortType) {
        ApiResponse userModels = depositService.getDepositPagination(accountNo,page,size,sortCol,sortType);
        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }
}
