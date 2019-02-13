package com.yacht.banking.service;

public interface AccountInterface {

    AccountHolder getAccountHolder();
    boolean validatePin(int attemptedPin);
    double getBalance();
    Long getAccountNumber();
    void creditAccount(double amount);
    boolean debitAccount(double amount);

}