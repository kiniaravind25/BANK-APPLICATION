package aravind.kini.bankingApplication.controller;


import aravind.kini.bankingApplication.dto.AccountDto;

import aravind.kini.bankingApplication.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/accounts")
public class Controller {

    @Autowired
    AccountService accountService;

    // API TO ADD ACCOUNT
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }

    // API TO GET ACCOUNTS BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getBalance(@PathVariable Long id) {
        AccountDto accountDto = accountService.getBalance(id);
        return ResponseEntity.ok(accountDto);
    }

    // API TO GET DEPOSIT AMOUNT
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amounts");
        AccountDto accountDto = accountService.depositAmount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //API TO WITHDRAW AMOUNT
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAccount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amounts");
        AccountDto accountDto = accountService.withdrawAmount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // API TO GET ALL ACCOUNTS
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);

    }

    // API TO DELETE ACCOUNTS
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }


}
