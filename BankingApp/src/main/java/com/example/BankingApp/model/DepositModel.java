package com.example.BankingApp.model;
import com.example.BankingApp.entity.Deposit;
import com.example.BankingApp.util.ConvertDate;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DepositModel {
    private Long id;
    private Double amount;
    private String transactionId;
    private AccountsModel accountsModel;
    private String depositeTime;
    private Long accountNo;
    public DepositModel SetDepositModel(Deposit deposit,AccountsModel accountsModel){
        this.setId(deposit.getId());
        this.setAmount(deposit.getAmount());
        this.setTransactionId(deposit.getTransactionId());
        this.setAccountsModel(accountsModel);
        Date date=deposit.getDepositTime().getTime(); //convert calender to date
        this.setDepositeTime(ConvertDate.dateToString(date,ConvertDate.YYYY_MM_DD));
        this.setAccountNo(accountsModel.getAccountNo());
        return this;
    }

}
