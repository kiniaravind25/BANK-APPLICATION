package aravind.kini.bankingApplication.service.impl;

import aravind.kini.bankingApplication.dto.AccountDto;
import aravind.kini.bankingApplication.entity.Account;
import aravind.kini.bankingApplication.mapper.AccountMapper;
import aravind.kini.bankingApplication.repository.AccountRepo;
import aravind.kini.bankingApplication.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepo accountRepo;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getBalance(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() >= 0) {
            account.setBalance(account.getBalance() + amount);
        } else {
            log.error("Invalid Amount");
        }
        Account saved = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(saved);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw (new RuntimeException("Invalid Amount"));
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saved = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(saved);


    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepo.deleteById(id);


    }
}
