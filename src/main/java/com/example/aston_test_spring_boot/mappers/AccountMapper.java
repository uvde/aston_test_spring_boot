package com.example.aston_test_spring_boot.mappers;

import com.example.aston_test_spring_boot.models.dto.AccountDto;
import com.example.aston_test_spring_boot.models.entities.Account;

import java.util.List;

public interface AccountMapper {
    List<AccountDto> accountListEntityToListAccountDto(List<Account> accounts);
    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);
}
