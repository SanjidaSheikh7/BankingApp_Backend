package com.example.BankingApp.service;

import com.example.BankingApp.entity.AccountType;
import com.example.BankingApp.entity.Education;
import com.example.BankingApp.entity.Gender;
import com.example.BankingApp.entity.User;
import com.example.BankingApp.exception.NotFoundException;
import com.example.BankingApp.model.*;
import com.example.BankingApp.repository.AccountRepository;
import com.example.BankingApp.repository.EducationRepository;
import com.example.BankingApp.repository.GenderRepository;
import com.example.BankingApp.repository.UserRepository;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final EducationRepository educationRepository;
    private final AccountRepository accountRepository;

    @Override
    public UserModel createUser(UserModel userModel) {
        Gender gender=genderRepository.findById(userModel.getGenderId())
                .orElseThrow(()->new NotFoundException("Invalid Gender Type" +
                 userModel.getGenderId() ));
        Education education=educationRepository.findById(userModel.getEducationId())
                .orElseThrow(()->new NotFoundException("Degree type not found" +
                        userModel.getEducationId() ));
        AccountType accountType=accountRepository.findById(userModel.getAccountID())
                .orElseThrow(()->new NotFoundException("Invalid Account Type" +
                        userModel.getAccountID() ));
        User user=new User().SetUser(userModel,education,gender,accountType);
        user=userRepository.save(user);
        EducationModel educationModel=new EducationModel().SetEducationModel(education);
        GenderModel genderModel=new GenderModel().SetGenderModel(gender);
        AccountModel accountModel=new AccountModel().SetAccountModel(accountType);
        return new UserModel().SetUserModel(user,educationModel,genderModel,accountModel);
    }

    @Override
    public ApiResponse getUserPagination(String UserName, int page, int size,
                                         String sortCol,String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<User> userPage;
        if(StringUtils.isNotBlank(UserName)){
            userPage=userRepository.findAllByNameContainsIgnoreCase(UserName,pageable);
        }else {
            userPage=userRepository.findAll(pageable);
        }

        List<UserModel> userModels=new ArrayList<>();
        if(!userPage.isEmpty()){
            userModels= userPage.getContent().stream()
                    .map(user -> new UserModel().SetUserModel(user,
                                 new EducationModel().SetEducationModel(user.getEducation()),
                                 new GenderModel().SetGenderModel(user.getGender()),
                                 new AccountModel().SetAccountModel(user.getAccountType())
                    )).toList();
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(userModels,
                userPage.getTotalElements(),userPage.getTotalPages(),userPage.hasNext(),
                userPage.hasPrevious(),page);
        return apiResponse;
    }

    @Override
    public UserModel getUserById(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("User with id " + id + "not found!"));
        EducationModel educationModel=new EducationModel().SetEducationModel(user.getEducation());
        GenderModel genderModel=new GenderModel().SetGenderModel(user.getGender());
        AccountModel accountModel=new AccountModel().SetAccountModel(user.getAccountType());
        return new UserModel().SetUserModel(user,educationModel,genderModel,accountModel);
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel) {
        User existingUser=userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("User with id" + id + "not exists!"));
        Education education=educationRepository.findById(userModel.getEducationId())
                .orElseThrow(()->new NotFoundException("Degree with id" + id + "not exists!"));
        Gender gender=genderRepository.findById(userModel.getGenderId())
                .orElseThrow(()->new NotFoundException("Gender with id" + id + "not exists!"));
        AccountType accountType=accountRepository.findById(userModel.getAccountID())
                .orElseThrow(()->new NotFoundException("Account Type with id" + id + "not exists!"));
        existingUser=existingUser.UpdateUser(userModel,education,gender,accountType);
        existingUser=userRepository.save(existingUser);
        EducationModel educationModel=new EducationModel().SetEducationModel(education);
        GenderModel genderModel=new GenderModel().SetGenderModel(gender);
        AccountModel accountModel=new AccountModel().SetAccountModel(accountType);
        return new UserModel().SetUserModel(existingUser,educationModel,genderModel,accountModel);
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }
}
