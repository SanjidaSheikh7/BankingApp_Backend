package com.example.BankingApp.service;

import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.entity.Withdraw;
import com.example.BankingApp.model.*;
import com.example.BankingApp.repository.DepositRepository;
import com.example.BankingApp.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    @Override
    public ApiResponse getAllTransaction(String transactionType,Long accountNo, int page, int size,
                                         String sortCol, String sortType) {
        List<TransactionModel> transactionModelList=new ArrayList<>();
        Page<Deposit> depositPage = Page.empty();;
        Page<Withdraw> withdrawPage=Page.empty();;
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        if(transactionType.equalsIgnoreCase("deposit")){
            depositPage=depositRepository.findAll(pageable);
        } else if(transactionType.equalsIgnoreCase("withdraw")){
            withdrawPage=withdrawRepository.findAll(pageable);
        } else if (accountNo!=null){
            depositPage= depositRepository.findAllByAccounts_AccountNo(accountNo,pageable);
            withdrawPage= withdrawRepository.findAllByAccounts_AccountNo(accountNo,pageable);
        } else{
            depositPage=depositRepository.findAll(pageable);
            withdrawPage=withdrawRepository.findAll(pageable);
        }
        if(!depositPage.isEmpty()){
            List<TransactionModel> depositTransectionList = depositPage.getContent().stream()
                    .map(deposit -> new TransactionModel().SetTransactionModelFromDeposit(deposit))
                    .toList();
            if(CollectionUtils.isNotEmpty(depositTransectionList)){
                transactionModelList.addAll(depositTransectionList);
            }
        }

        if(!withdrawPage.isEmpty()){
            List<TransactionModel> withdrawTransectionList = withdrawPage.getContent().stream()
                    .map(withdraw -> new TransactionModel().SetTransactionModelFromWithdraw(withdraw))
                    .toList();
            if(CollectionUtils.isNotEmpty(withdrawTransectionList)){
                transactionModelList.addAll(withdrawTransectionList);
            }
        }

        transactionModelList= transactionModelList.stream()
                .sorted(Comparator.comparing(TransactionModel::getTransactionTime))
                .toList();
        Page<TransactionModel> transactionModelPage = convertListToPage(transactionModelList, pageable);
        ApiResponse apiResponse=new ApiResponse().SetResponse(transactionModelList,
                transactionModelPage.getTotalElements(),transactionModelPage.getTotalPages(),transactionModelPage.hasNext(),
                transactionModelPage.hasPrevious(),page);
        return apiResponse;
    }

    public Page<TransactionModel> convertListToPage(List<TransactionModel> transactionModelList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > transactionModelList.size() ? transactionModelList.size() : (start + pageable.getPageSize());
        return new PageImpl<>(transactionModelList.subList(start, end), pageable, transactionModelList.size());
    }

}
