package com.example.aston_test_spring_boot.services;

import com.example.aston_test_spring_boot.mappers.impl.AccountMapperImpl;
import com.example.aston_test_spring_boot.models.dto.AccountDto;
import com.example.aston_test_spring_boot.models.entities.Account;
import com.example.aston_test_spring_boot.repositories.AccountRepository;
import com.example.aston_test_spring_boot.services.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private AccountMapperImpl accountMapper;

    public List<Account> accounts;

    @BeforeEach
    public void addList(){
        log.info("BeforeEach");
        //arrange
        accounts = new ArrayList<>();
        accounts.add(new Account(new BigDecimal(1000), "vasia"));
        accounts.add(new Account(new BigDecimal(5000), "sofiy"));
        accounts.add(new Account(new BigDecimal(3000), "vlad"));
        accounts.add(new Account(new BigDecimal(3000), "alexei"));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(accounts.get(0)));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(accounts.get(1)));
        when(accountRepository.findById(4L)).thenReturn(Optional.ofNullable(null));
        when(accountRepository.findAll()).thenReturn(accounts);

    }

    @Test
    public void correctGetTest(){

        log.info("correctGetTest");
        //act
        List<AccountDto> accountDtoList = accountService.getAllAccount();
        //assert
        assertEquals(4, accountDtoList.size());
    }

    @Test
    public void correctTransaction(){
        log.info("correctTransaction");
        //act
       accountService.createTransaction(1l, 2l, new BigDecimal(500));
       //assert
       assertEquals(new BigDecimal(500), accounts.get(0).getValue());
       assertEquals(new BigDecimal(5500), accounts.get(1).getValue());
       verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    public void incorrectValue(){
        //arrange
        BigDecimal value = new BigDecimal(1001);
        //act
        IllegalArgumentException trows = assertThrows( IllegalArgumentException.class, () ->{
        accountService.createTransaction(1l, 2l, value);
        });
        //assert
        assertEquals("there isn`t any money" + value, trows.getMessage());
        assertEquals(new BigDecimal(1000), accounts.get(0).getValue());
        assertEquals(new BigDecimal(5000), accounts.get(1).getValue());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void incorrectAccount(){
        //arrange
        BigDecimal value = new BigDecimal(1001);
        //act
        NullPointerException trows = assertThrows(NullPointerException.class, ()->{
            accountService.createTransaction(4l, 2l, value);
        });
        //assert
        assertEquals("invalid sender", trows.getMessage());
    }

    @AfterEach
    public void teardown(){
        log.info("clear");
        accounts.clear();
    }
}
