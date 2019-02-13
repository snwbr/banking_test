package com.yacht.banking.model;

import com.yacht.banking.service.Account;
import com.yacht.banking.service.BankInterface;

import java.util.Collections;
import java.util.LinkedHashMap;

public class Bank implements BankInterface {

    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
        this.accounts = new LinkedHashMap<>();
    }

    public Account getAccount(Long accountNumber) throws Exception {
        validateAccounts();
        return accounts.get(accountNumber);
    }

    public void addAuthorizedUserToAccount(Long accountNumber, Person person){
        ((CommercialAccount)this.accounts.get(accountNumber)).addAuthorizedUser(person);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit, Person authorizedPerson){
        Long newAccountNumber = getNewAccountNumber();
        CommercialAccount commercialAccount = new CommercialAccount(company, newAccountNumber, pin, startingDeposit, authorizedPerson);
        accounts.put(newAccountNumber, commercialAccount);
        return newAccountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit){
        Long newAccountNumber = getNewAccountNumber();
        ConsumerAccount consumerAccount = new ConsumerAccount(person, newAccountNumber, pin, startingDeposit);
        accounts.put(newAccountNumber, consumerAccount);
        return newAccountNumber;
    }

    private Long getNewAccountNumber(){
        return accounts.size() > 0 ? Collections.max(accounts.keySet()) + 1 : 1L;
    }

    public boolean authenticateUser(Long accountNumber, int pin) throws Exception {
        validateAccounts();
        return accounts.get(accountNumber).validatePin(pin);
    }

    public double getBalance(Long accountNumber) throws Exception {
        validateAccounts();
        return accounts.get(accountNumber).getBalance();
    }

    public void credit(Long accountNumber, double amount) throws Exception {
        validateAccounts();
        accounts.get(accountNumber).creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) throws Exception {
        validateAccounts();
        return accounts.get(accountNumber).debitAccount(amount);
    }

    private void validateAccounts() throws Exception {
        if (accounts.size() == 0)
            throw new Exception("There are no accounts registered in Bank");
    }
}
