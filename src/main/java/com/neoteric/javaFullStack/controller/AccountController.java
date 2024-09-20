package com.neoteric.javaFullStack.controller;

import com.neoteric.javaFullStack.exception.AccountCreationFailedException;

import com.neoteric.javaFullStack.model.Account;
import com.neoteric.javaFullStack.service.AccountService;
import com.neoteric.javaFullStack.service.JPAAccountSearchService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AccountController {
    @PostMapping(value = "api/createAccount",consumes = "application/json",produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account) throws AccountCreationFailedException {
        AccountService accountService= new AccountService();

        String accountNumber=accountService.createAccount(account);
        account.setAccount_number(accountNumber);
        //accountService.createAccount(account);
        return account;

    }

    @PostMapping(value = "api/createAccountUsingHibernate",consumes = "application/json",produces = "application/json")
    public Account  getAccountNumberUsingHibernate(@RequestBody Account account) throws AccountCreationFailedException {
        AccountService accountService= new AccountService();

        String accNO=accountService.createAccountUsingHibernate(account);
        account.setAccount_number(accNO);
        return account;

    }
    @PostMapping(value = "api/createAccountUsingUI",consumes = "application/json",produces = "application/json")
    public String  getAccountNumberUsingHibernateFromUI(@RequestBody Account account)
            throws AccountCreationFailedException   {
        AccountService accountService = new AccountService();

        String accNO=accountService.createAccountUsingHibernateFromUI(account);
        account.setAccount_number(accNO);
        return account.getAccount_number();

    }

    @GetMapping(value = "api/searchAccountUsingUI",consumes = "application/json"
    ,produces = "application/json")
    public Account  getAccountNumberUsingHibernatUI(@RequestHeader ("accountNumberFromUI")
                                                        String account_number)
            throws AccountCreationFailedException   {


        AccountService accountService = new AccountService();

        Account account=accountService.searchAccountUsingHibernateHeaders(account_number);

        return account;

    }


    @PostMapping(value = "api/createAccountUsingJPA",consumes = "application/json",produces = "application/json")

public String createAccountUsingJPA(@RequestBody Account account){
        AccountService accountService= new AccountService();
        String accountNumber=accountService.createAccountUsingJPa(account);
        account.setAccount_number(accountNumber);
        return accountNumber;

    }

    @GetMapping(value = "api/searchAccountUsingJPA",consumes = "application/json"
            ,produces = "application/json")
    public Account  getAccountNumberUsingJPA(@RequestHeader ("accountNumberFromUI") String accountNumber){
        JPAAccountSearchService jpaAccountSearchService= new JPAAccountSearchService();
         Account account= jpaAccountSearchService.searchAccountUsingJPA(accountNumber);
       return account;
    }
}
