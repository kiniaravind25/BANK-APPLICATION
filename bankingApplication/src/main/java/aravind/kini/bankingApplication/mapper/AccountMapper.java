package aravind.kini.bankingApplication.mapper;

import aravind.kini.bankingApplication.dto.AccountDto;
import aravind.kini.bankingApplication.entity.Account;

import java.util.Optional;

public class AccountMapper {

    public static Account maptoAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto maptoAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
return accountDto;
    }
}

