package com.yacht.banking.model;

import com.yacht.banking.service.Account;

import java.util.ArrayList;
import java.util.List;

public class CommercialAccount extends Account {

    List<Person> authorizedUsers;

    public CommercialAccount(Company company, Long accountNumber, int pin, double startingDeposit, Person authorizedUser){
        super(company, accountNumber, pin, startingDeposit);
        this.authorizedUsers = new ArrayList<Person>();
        this.authorizedUsers.add(authorizedUser);
    }

    public void addAuthorizedUser(Person person){
        this.authorizedUsers.add(person);
    }

    public boolean isAuthorizedUser(Person person){
        for (Person p: authorizedUsers){
            if (p.getIdNumber() == person.getIdNumber())
                return true;
        }
        return false;
    }
}
