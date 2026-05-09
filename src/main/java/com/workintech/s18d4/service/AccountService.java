package com.workintech.s18d4.service;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountResponse> findAll();
    AccountResponse findById(Long id);
    AccountResponse save(Account account, Long customerId);
    AccountResponse delete(Long id);
    AccountResponse update(Long customerId, Account account);
}
