package com.yacht.banking.service;

import com.yacht.banking.model.Company;
import com.yacht.banking.model.Person;

public interface BankInterface {

    Long openCommercialAccount(Company company, int pin, double startingDeposit, Person authorizedPerson);
    Long openConsumerAccount(Person person, int pin, double startingDeposit);
    boolean authenticateUser(Long accountNumber, int pin) throws Exception;
    double getBalance(Long accountNumber) throws Exception;
    void credit(Long accountNumber, double amount) throws Exception;
    boolean debit(Long accountNumber, double amount) throws Exception;

}
