package com.yacht.banking.service;

public interface TransactionInterface {

    double getBalance() throws Exception;
    void credit(double amount) throws Exception;
    boolean debit(double amount) throws Exception;

}
