package com.workintech.s18d4.service;


import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.mapper.AccountMapper;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();

        return accountMapper.toAccountResponseList(accounts);
    }

    @Override
    public AccountResponse findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse save(Account account, Long customerId)
    {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        account.setCustomer(customer);
        accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
        } else {
            throw new RuntimeException("Account not found");
        }
        return accountMapper.toAccountResponse(account.get());
    }

    @Override
    public AccountResponse update(Long id, Account account) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        Account updateAccount;
        //db de accountlar bir tablolar halinde tutuluyor ben bunların içinde taleb edilen id'de bir account var mı(varsa onu update edicez yoksa create edicez) onu kontrol ediyorum
        //önce request edilen account id si null mı kontrol et
        if(account.getId() != null) {
            //null değilse account u bul
            updateAccount = accountRepository.findById(account.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
            // böyle bir account varsa kimin üzerine kayıtlı, talebi yapan customer a mı ait
            if(updateAccount.getCustomer().getId() != customer.getId()) {
                throw new RuntimeException("This account does not belong to this customer");
            } else {
                updateAccount.setAccountName(account.getAccountName());
                updateAccount.setMoneyAmount(account.getMoneyAmount());
                Account updatedAccount = accountRepository.save(updateAccount);
                return accountMapper.toAccountResponse(updatedAccount);
            }
        //eğer bir id belirtilmemişse
        } else {
            Account newAccount = new Account();
            newAccount.setCustomer(customer);
            newAccount.setAccountName(account.getAccountName());
            newAccount.setMoneyAmount(account.getMoneyAmount());
            Account updatedAccount = accountRepository.save(newAccount);
            return accountMapper.toAccountResponse(updatedAccount);
        }

    };
}
