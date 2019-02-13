package com.yacht.banking.model;

import com.yacht.banking.service.AccountHolder;

public class Person extends AccountHolder {

    String firstName;
    String lastName;

    public Person(String firstName, String lastName, int idNumber){
        super(idNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
