package com.workintech.s18d4.mapper;


import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class AccountMapper {

    @Autowired
    private final CustomerMapper customerMapper;

    public AccountMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountName(),
                account.getMoneyAmount(),
                //burası CustomerResponse tipinde istediği için account.getCustomer ile çektiğimiz customer ı CustomerResponse a çevirdik.
                customerMapper.toCustomerResponse(account.getCustomer())
        );
    }

    public List<AccountResponse> toAccountResponseList(List<Account> accounts) {

        return accounts.stream().map(this::toAccountResponse).toList();

        /*
        List<AccountResponse> accountResponseList = new ArrayList<>();
        for (Account account : accounts) {
            AccountResponse accountResponse = toAccountResponse(account);
            accountResponseList.add(accountResponse);
        }
        return accountResponseList;

         */
    }


    /*
    public Account toAccount(AccountResponse accountResponse) {
        return new Account(accountResponse.id(), accountResponse.accountName(),accountResponse.moneyAmount());
    }
    */

}
