package com.example.aston_test_spring_boot.repositories;

import com.example.aston_test_spring_boot.models.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
