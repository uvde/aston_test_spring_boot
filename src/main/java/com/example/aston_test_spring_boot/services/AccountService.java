package com.example.aston_test_spring_boot.services;

import com.example.aston_test_spring_boot.models.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountDto getById(Long id);
    List<AccountDto> getAllAccount();
    Long saveAccount(AccountDto accountDto);
    void updateAccount(Long id, AccountDto accountDto);
    void createTransaction(Long fromId, Long toId, BigDecimal value);
}
