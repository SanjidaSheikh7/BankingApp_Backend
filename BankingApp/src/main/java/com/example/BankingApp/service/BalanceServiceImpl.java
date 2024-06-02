package com.example.BankingApp.service;

import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Balance;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.AccountsModel;
import com.example.BankingApp.model.BalanceModel;
import com.example.BankingApp.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository balanceRepository;
    @Override
    public BalanceModel createBalance(Accounts account) {
        Balance accounts=this.balanceRepository.findByAccounts_Id(account.getId());
        Balance balance = new Balance();
        if(accounts==null){
            balance=balance.SetBalance(account);
            balance=this.balanceRepository.save(balance);
        }
        AccountsModel accountsModel=new AccountsModel().SetAccountModel(account);
        return new BalanceModel().SetBalanceModel(balance,accountsModel);
    }
@Override
public void updateCurrentBalance(String balanceType, Double amount, Accounts account) {
    Balance balance = this.balanceRepository.findByAccounts_Id(account.getId());
    if (balance != null) {
        Double currentAmount = balance.getCurrentBalance();
        if ("deposit".equals(balanceType)) {
            currentAmount += amount;
        } else {
            currentAmount -= amount;
        }
        balance.setCurrentBalance(currentAmount);
        this.balanceRepository.save(balance);
    } else {
        throw new NotFoundException("Balance record not found for the account");
    }
}

}
