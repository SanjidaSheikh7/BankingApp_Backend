package com.example.BankingApp.service;

import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.entity.Accounts;
import com.example.BankingApp.entity.Education;
import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.exception.NotValid;
import com.example.BankingApp.model.*;
import com.example.BankingApp.repository.AccountTypeRepository;
import com.example.BankingApp.repository.EducationRepository;
import com.example.BankingApp.repository.GenderRepository;
import com.example.BankingApp.repository.AccountsRepository;
import com.example.BankingApp.util.ConvertDate;
import com.example.BankingApp.util.EmailValidator;
import com.example.BankingApp.util.PhoneNumberValidator;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final GenderRepository genderRepository;
    private final EducationRepository educationRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final PhoneNumberValidator phoneNumberValidator;
    private final EmailValidator emailValidator;

    @Override
    public AccountsModel createAccount(AccountsModel accountsModel) {
        if (!phoneNumberValidator.isValid(accountsModel.getPhoneNo())) {
            throw new NotValid("Invalid phone number: " + accountsModel.getPhoneNo());
        }
        if (!emailValidator.isValidEmail(accountsModel.getEmail())) {
            throw new NotValid("Invalid Email Address: " + accountsModel.getEmail());
        }
        if(ConvertDate.calculateAge(accountsModel.getDob())<18){
            throw new NotValid("Under 18 years can not open an account ");
        }
        Gender gender=genderRepository.findById(accountsModel.getGenderId())
                .orElseThrow(()->new NotFoundException("Invalid Gender Type" +
                 accountsModel.getGenderId() ));
        Education education=educationRepository.findById(accountsModel.getEducationId())
                .orElseThrow(()->new NotFoundException("Degree type not found" +
                        accountsModel.getEducationId() ));
        AccountType accountType= accountTypeRepository.findById(accountsModel.getAccountID())
                .orElseThrow(()->new NotFoundException("Invalid Account Type" +
                        accountsModel.getAccountID() ));
        Accounts accounts =new Accounts().SetAccount(accountsModel,education,gender,accountType);
        accounts = accountsRepository.save(accounts);
        EducationModel educationModel=new EducationModel().SetEducationModel(education);
        GenderModel genderModel=new GenderModel().SetGenderModel(gender);
        AccountTypeModel accountTypeModel =new AccountTypeModel().SetAccountModel(accountType);
        return new AccountsModel().SetAccountModel(accounts,educationModel,genderModel, accountTypeModel);
    }

    @Override
    public ApiResponse getAccountPagination(String accountName, int page, int size,
                                         String sortCol,String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<Accounts> accountsPage;
        if(StringUtils.isNotBlank(accountName)){
            accountsPage= accountsRepository.findAllByNameContainsIgnoreCase(accountName,pageable);
        }else {
            accountsPage= accountsRepository.findAll(pageable);
        }

        List<AccountsModel> accountsModels =new ArrayList<>();
        if(!accountsPage.isEmpty()){
            accountsModels = accountsPage.getContent().stream()
                    .map(accounts -> new AccountsModel().SetAccountModel(accounts,
                                 new EducationModel().SetEducationModel(accounts.getEducation()),
                                 new GenderModel().SetGenderModel(accounts.getGender()),
                                 new AccountTypeModel().SetAccountModel(accounts.getAccountType())
                    )).toList();
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(accountsModels,
                accountsPage.getTotalElements(),accountsPage.getTotalPages(),accountsPage.hasNext(),
                accountsPage.hasPrevious(),page);
        return apiResponse;
    }

    @Override
    public AccountsModel getAccountById(Long id) {
        Accounts accounts = accountsRepository.findById(id)
                .orElseThrow(()->new NotFoundException("User with id " + id + "not found!"));
        EducationModel educationModel=new EducationModel().SetEducationModel(accounts.getEducation());
        GenderModel genderModel=new GenderModel().SetGenderModel(accounts.getGender());
        AccountTypeModel accountTypeModel =new AccountTypeModel().SetAccountModel(accounts.getAccountType());
        return new AccountsModel().SetAccountModel(accounts,educationModel,genderModel, accountTypeModel);
    }

    @Override
    public AccountsModel updateAccount(Long id, AccountsModel accountsModel) {
        Accounts existingAccounts = accountsRepository.findById(id)
                .orElseThrow(()->new NotFoundException("User with id" + id + "not exists!"));
        Education education=educationRepository.findById(accountsModel.getEducationId())
                .orElseThrow(()->new NotFoundException("Degree with id" + id + "not exists!"));
        Gender gender=genderRepository.findById(accountsModel.getGenderId())
                .orElseThrow(()->new NotFoundException("Gender with id" + id + "not exists!"));
        AccountType accountType= accountTypeRepository.findById(accountsModel.getAccountID())
                .orElseThrow(()->new NotFoundException("Account Type with id" + id + "not exists!"));
        existingAccounts = existingAccounts.UpdateUser(accountsModel,education,gender,accountType);
        existingAccounts = accountsRepository.save(existingAccounts);
        EducationModel educationModel=new EducationModel().SetEducationModel(education);
        GenderModel genderModel=new GenderModel().SetGenderModel(gender);
        AccountTypeModel accountTypeModel =new AccountTypeModel().SetAccountModel(accountType);
        return new AccountsModel().SetAccountModel(existingAccounts,educationModel,genderModel, accountTypeModel);
    }

    @Override
    public void deleteAccount(Long id) {
       accountsRepository.deleteById(id);
    }
}
