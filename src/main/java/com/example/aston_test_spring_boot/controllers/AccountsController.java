package com.example.aston_test_spring_boot.controllers;

import com.example.aston_test_spring_boot.models.dto.AccountDto;
import com.example.aston_test_spring_boot.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<AccountDto> getAllAccount(){
        return accountService.getAllAccount();
    }

    @GetMapping("/{fromId}/{toId}/{value}")
    public void createTransaction(@PathVariable("fromId") Long fromId,
                                  @PathVariable("toId") Long toId,
                                  @PathVariable("value") BigDecimal value){
        accountService.createTransaction(fromId, toId, value);
    }
}
