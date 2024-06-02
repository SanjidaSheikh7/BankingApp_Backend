package com.example.BankingApp.service;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.*;
import com.example.BankingApp.repository.AccountsRepository;
import com.example.BankingApp.repository.DepositRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DepositServiceImpl implements DepositService{
    private final DepositRepository depositRepository;
    private final AccountsRepository accountsRepository;
    private final BalanceService balanceService;
    @Override
    public DepositModel createDeposit(DepositModel depositModel) {
         Accounts account=this.accountsRepository.findByAccountNo(depositModel.getAccountNo());
         Deposit deposit;
         if(account !=null){
             deposit=new Deposit().SetDeposit(depositModel,account);
             deposit=depositRepository.save(deposit);
             balanceService.updateCurrentBalance("deposit",deposit.getAmount(),account);
         }else{
             throw new NotFoundException("Account not exists");
         }
         AccountsModel accountsModel=new AccountsModel().SetAccountModel(account);
         return new DepositModel().SetDepositModel(deposit,accountsModel);
    }

    @Override
    public ApiResponse getDepositPagination(Long accountNo, int page, int size, String sortCol, String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<Deposit> depositPage;
        if(accountNo!=null){
            depositPage= depositRepository.findAllByAccounts_AccountNo(accountNo,pageable);
        }else {
            depositPage= depositRepository.findAll(pageable);
        }

        List<DepositModel> depositModels =new ArrayList<>();
        if(!depositPage.isEmpty()){
            depositModels = depositPage.getContent().stream()
                    .map(deposits -> new DepositModel().SetDepositModel(deposits,
                            new AccountsModel().SetAccountModel(deposits.getAccounts())
                    )).toList();
        }else {
            throw new NotFoundException("No Deposit in the database");
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(depositModels,
                depositPage.getTotalElements(),depositPage.getTotalPages(),depositPage.hasNext(),
                depositPage.hasPrevious(),page);
        return apiResponse;
    }
}
