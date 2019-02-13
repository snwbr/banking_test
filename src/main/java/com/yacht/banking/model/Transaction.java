package com.yacht.banking.model;

import com.yacht.banking.service.TransactionInterface;

import java.util.HashMap;

public class Transaction implements TransactionInterface {

    Long accountNumber;
    Bank bank;

    public Transaction(Bank bank, Long accountNumber, int attemptedPin) throws Exception {
        if (!bank.authenticateUser(accountNumber, attemptedPin))
            throw new Exception("PIN number for given account is incorrect.");
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public double getBalance() throws Exception {
        return this.bank.getBalance(this.accountNumber);
    }

    public void credit(double amount) throws Exception {
        this.bank.credit(this.accountNumber, amount);
    }

    public boolean debit(double amount) throws Exception {
        return this.bank.debit(this.accountNumber, amount);
    }
}
