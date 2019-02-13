package com.yacht.banking.service;

public abstract class Account implements AccountInterface{

    protected
        AccountHolder accountHolder;
        Long accountNumber;
        int pin;
        double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = startingDeposit;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(int attemptedPin){
        return attemptedPin == this.pin;
    }

    public void creditAccount(double amount){
        this.balance += amount;
    }

    public boolean debitAccount(double amount){
        if ( this.balance - amount < 0 )
            return false;
        this.balance -= amount;
        return true;
    }
}
