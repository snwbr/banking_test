package com.yacht.banking.model;

import com.yacht.banking.service.Account;

public class ConsumerAccount extends Account {

    public ConsumerAccount(Person person, Long accountNumber, int pin, double currentBalance){
        super(person, accountNumber, pin, currentBalance);
    }
}
