package com.yacht.banking.model;

import com.yacht.banking.service.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    Bank bank;
    static Double STARTING_DEPOSIT_COMMERCIAL = 1500000.0;
    static Double STARTING_DEPOSIT_CONSUMER = 4000.0;
    static Long COMMERCIAL_ACCOUNT, CONSUMER_ACCOUNT;

    public void BankInit(){
        bank = new Bank();
        Company company = new Company("Company Test", 1);
        Person person = new Person("Person Name test", "Person Lastname Test", 2);
        COMMERCIAL_ACCOUNT = bank.openCommercialAccount(company, 1111, STARTING_DEPOSIT_COMMERCIAL, person);
        CONSUMER_ACCOUNT = bank.openConsumerAccount(person, 2222, STARTING_DEPOSIT_CONSUMER);
    }

    @Test
    public void emptyAccounts() throws Exception {
        bank = new Bank();
        try{
            bank.authenticateUser(9L, 3333);
        }catch (Exception e){
            assertEquals("Expecting an exception for an empty Account Hash","There are no accounts registered in Bank", e.getMessage());
        }
    }

    @Test
    public void addAuthorizedUserToAccount() throws Exception {
        BankInit();

        Person authorizedPerson = new Person("Authorized Name", "Authorized Last Name", 3);

        bank.addAuthorizedUserToAccount(COMMERCIAL_ACCOUNT, authorizedPerson);
        CommercialAccount newAccount = (CommercialAccount) bank.getAccount(COMMERCIAL_ACCOUNT);

        assertTrue("Validating new authorized user", newAccount.isAuthorizedUser(authorizedPerson));
    }

    @Test
    public void openCommercialAccount() throws Exception {
        BankInit();

        Company company = new Company("Company test 2", 3);
        Person authorizedPerson = new Person("Authorized Name", "Authorized Last Name", 3);
        Person nonAuthorizedPerson = new Person("Non Authorized Name", "Non Authorized Last Name", 4);
        Long newId = bank.openCommercialAccount(company, 3333, STARTING_DEPOSIT_COMMERCIAL, authorizedPerson);
        CommercialAccount newAccount = (CommercialAccount) bank.getAccount(newId);
        Company newCompany = (Company) newAccount.getAccountHolder();

        assertTrue("Authenticating new user", bank.authenticateUser(newId, 3333));
        assertEquals("Getting new account", newId, newAccount.getAccountNumber());
        assertEquals("Getting new Company Name", "Company test 2", newCompany.getCompanyName());
        assertTrue("Validating new Account Authorized user - Valid", newAccount.isAuthorizedUser(authorizedPerson));
        assertFalse("Validating new Account Authorized user - Invalid", newAccount.isAuthorizedUser(nonAuthorizedPerson));
    }

    @Test
    public void openConsumerAccount() throws Exception {
        BankInit();

        Person person = new Person("Person Name test 2", "Person Lastname test 2", 3);
        Long newId = bank.openConsumerAccount(person, 3333, STARTING_DEPOSIT_CONSUMER);
        ConsumerAccount newAccount = (ConsumerAccount) bank.getAccount(newId);
        Person newPerson = (Person) newAccount.getAccountHolder();

        assertTrue("Authenticating new user", bank.authenticateUser(newId, 3333));
        assertEquals("Getting new account", newId, newAccount.getAccountNumber());
        assertEquals("Getting new Person Name", "Person Name test 2", newPerson.getFirstName());
        assertEquals("Getting new Person Last Name", "Person Lastname test 2", newPerson.getLastName());
    }

    @Test
    public void authenticateUser() throws Exception {
        BankInit();

        // Commercial account
        assertTrue("Authenticating user - Valid PIN - Commercial account",bank.authenticateUser(COMMERCIAL_ACCOUNT, 1111));
        assertFalse("Authenticating user - Invalid PIN - Commercial account",bank.authenticateUser(COMMERCIAL_ACCOUNT, 3333));

        // Consumer account
        assertTrue("Authenticating user - Valid PIN - Consumer account",bank.authenticateUser(CONSUMER_ACCOUNT, 2222));
        assertFalse("Authenticating user - Invalid PIN - Consumer account",bank.authenticateUser(CONSUMER_ACCOUNT, 3333));
    }

    @Test
    public void getBalance() throws Exception {
        BankInit();

        assertEquals("Checking balance - Commercial account", STARTING_DEPOSIT_COMMERCIAL, bank.getBalance(COMMERCIAL_ACCOUNT), 0);
        assertEquals("Checking balance - Consumer account", STARTING_DEPOSIT_CONSUMER, bank.getBalance(CONSUMER_ACCOUNT), 0);
    }

    @Test
    public void credit() throws Exception {
        BankInit();

        // Commercial Account
        bank.credit(COMMERCIAL_ACCOUNT, 1000);
        assertEquals("Checking balance - Crediting a valid amount - Commercial account", STARTING_DEPOSIT_COMMERCIAL + 1000.0, bank.getBalance(COMMERCIAL_ACCOUNT), 0);

        // Consumer Account
        bank.credit(CONSUMER_ACCOUNT, 1000);
        assertEquals("Checking balance - Crediting a valid amount - Consumer account", STARTING_DEPOSIT_CONSUMER + 1000.0, bank.getBalance(CONSUMER_ACCOUNT), 0);
    }

    @Test
    public void debit() throws Exception {
        BankInit();

        // Commercial Account
        assertTrue("Debiting a valid amount - Commercial Account", bank.debit(COMMERCIAL_ACCOUNT, 1000));
        assertEquals("Checking current balance", STARTING_DEPOSIT_COMMERCIAL - 1000.0, bank.getBalance(COMMERCIAL_ACCOUNT), 0);
        assertTrue("Debiting all balance - Commercial Account", bank.debit(COMMERCIAL_ACCOUNT, bank.getBalance(COMMERCIAL_ACCOUNT)));
        assertEquals("Checking current balance",0, bank.getBalance(COMMERCIAL_ACCOUNT), 0);
        assertFalse("Debiting more than the balance - Commercial Account", bank.debit(COMMERCIAL_ACCOUNT, 1000));
        assertEquals("Checking current balance",0, bank.getBalance(COMMERCIAL_ACCOUNT), 0);

        // Consumer Account
        assertTrue("Debiting a valid amount - Consumer Account", bank.debit(CONSUMER_ACCOUNT, 1000));
        assertEquals("Checking current balance", STARTING_DEPOSIT_CONSUMER - 1000.0, bank.getBalance(CONSUMER_ACCOUNT), 0);
        assertTrue("Debiting all balance - Consumer Account", bank.debit(CONSUMER_ACCOUNT, bank.getBalance(CONSUMER_ACCOUNT)));
        assertEquals("Checking current balance",0, bank.getBalance(CONSUMER_ACCOUNT), 0);
        assertFalse("Debiting more than the balance - Consumer Account", bank.debit(CONSUMER_ACCOUNT, 1000));
        assertEquals("Checking current balance",0, bank.getBalance(CONSUMER_ACCOUNT), 0);
    }
}