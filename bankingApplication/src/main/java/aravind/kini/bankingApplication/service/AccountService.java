package aravind.kini.bankingApplication.service;

import aravind.kini.bankingApplication.dto.AccountDto;
import aravind.kini.bankingApplication.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getBalance(Long id);

    AccountDto depositAmount(Long id, double amount);

    AccountDto withdrawAmount(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}
