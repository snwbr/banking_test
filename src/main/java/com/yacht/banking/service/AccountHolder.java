package com.yacht.banking.service;

public abstract class AccountHolder {

    protected int idNumber;

    public AccountHolder(int idNumber){
        this.idNumber = idNumber;
    }

    public int getIdNumber(){
        return idNumber;
    }
}
