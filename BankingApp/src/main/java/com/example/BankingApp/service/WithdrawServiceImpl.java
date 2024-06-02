package com.example.BankingApp.service;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.entity.Withdraw;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.model.ApiResponse;
import com.example.BankingApp.model.DepositModel;
import com.example.BankingApp.model.WithdrawModel;
import com.example.BankingApp.repository.AccountsRepository;
import com.example.BankingApp.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WithdrawServiceImpl implements WithdrawService{
    private final AccountsRepository accountsRepository;
    private final BalanceService balanceService;
    private final WithdrawRepository withdrawRepository;
    @Override
    public WithdrawModel createWithdraw(WithdrawModel withdrawModel) {
        Accounts account=this.accountsRepository.findByAccountNo(withdrawModel.getAccountNo());
        Withdraw withdraw;
        if(account !=null){
            withdraw=new Withdraw().SetWithdraw(withdrawModel,account);
            withdraw=withdrawRepository.save(withdraw);
            balanceService.updateCurrentBalance("withdraw",withdraw.getWithdrawAmount(),account);
        }else{
            throw new NotFoundException("Account not exists");
        }
        AccountsModel accountsModel=new AccountsModel().SetAccountModel(account);
        return new WithdrawModel().SetWithdrawModel(withdraw,accountsModel);
    }

    @Override
    public ApiResponse getWithdrawPagination(Long accountNo, int page, int size, String sortCol, String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<Withdraw> withdrawPage;
        if(accountNo!=null){
            withdrawPage= withdrawRepository.findAllByAccounts_AccountNo(accountNo,pageable);
        }else {
            withdrawPage= withdrawRepository.findAll(pageable);
        }

        List<WithdrawModel> withdrawModels =new ArrayList<>();
        if(!withdrawPage.isEmpty()){
            withdrawModels = withdrawPage.getContent().stream()
                    .map(withdraws -> new WithdrawModel().SetWithdrawModel(withdraws,
                            new AccountsModel().SetAccountModel(withdraws.getAccounts())
                    )).toList();
        }else {
            throw new NotFoundException("No Deposit in the database");
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(withdrawModels,
                withdrawPage.getTotalElements(),withdrawPage.getTotalPages(),withdrawPage.hasNext(),
                withdrawPage.hasPrevious(),page);
        return apiResponse;
    }
}
