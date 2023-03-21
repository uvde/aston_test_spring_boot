package com.example.aston_test_spring_boot.mappers.impl;

import com.example.aston_test_spring_boot.mappers.AccountMapper;
import com.example.aston_test_spring_boot.models.dto.AccountDto;
import com.example.aston_test_spring_boot.models.entities.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public List<AccountDto> accountListEntityToListAccountDto(List<Account> accounts) {
        return accounts.stream().map(this::accountToAccountDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto accountToAccountDto(Account account) {
        return new AccountDto(account.getValue(), account.getName());
    }
}
