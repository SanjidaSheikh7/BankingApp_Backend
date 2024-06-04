package com.example.BankingApp.service;

import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.entity.Withdraw;
import com.example.BankingApp.model.*;
import com.example.BankingApp.repository.DepositRepository;
import com.example.BankingApp.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ApiResponse getAllTransaction(String transactionType, int page, int size,
                                         String sortCol, String sortType) {
        List<TransactionModel> transactionModelList=new ArrayList<>();
        Page<Deposit> depositList = null;
        Page<Withdraw> withdrawList=null;
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        if(transactionType.equalsIgnoreCase("deposit")){
            depositList=depositRepository.findAll(pageable);
        } else if(transactionType.equalsIgnoreCase("withdraw")){
            withdrawList=withdrawRepository.findAll(pageable);
        } else{
            depositList=depositRepository.findAll(pageable);
            withdrawList=withdrawRepository.findAll(pageable);
        }
        if(!depositList.isEmpty()){
            List<TransactionModel> depositTransectionList = depositList.getContent().stream()
                    .map(deposit -> new TransactionModel().SetTransactionModelFromDeposit(deposit))
                    .toList();
            if(CollectionUtils.isNotEmpty(depositTransectionList)){
                transactionModelList.addAll(depositTransectionList);
            }
        }

        if(!withdrawList.isEmpty()){
            List<TransactionModel> withdrawTransectionList = withdrawList.getContent().stream()
                    .map(withdraw -> new TransactionModel().SetTransactionModelFromWithdraw(withdraw))
                    .toList();
            if(CollectionUtils.isNotEmpty(withdrawTransectionList)){
                transactionModelList.addAll(withdrawTransectionList);
            }
        }

        transactionModelList= transactionModelList.stream()
                .sorted(Comparator.comparing(TransactionModel::getTransactionTime))
                .toList();
        PagedListHolder transactionModelPage = new PagedListHolder(transactionModelList);

        ApiResponse apiResponse=new ApiResponse().SetTransactionResponse(transactionModelList,
                transactionModelPage.getNrOfElements(),transactionModelPage.getPageCount(),transactionModelPage.isLastPage(),
                transactionModelPage.isFirstPage(),page);
        return apiResponse;
    }

}
