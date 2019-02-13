package com.yacht.banking.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    Bank bank;
    Transaction transaction;
    static Double STARTING_DEPOSIT_COMMERCIAL = 1500000.0;
    static Double STARTING_DEPOSIT_CONSUMER = 4000.0;
    static Long COMMERCIAL_ACCOUNT, CONSUMER_ACCOUNT;

    public void TransactionInit() throws Exception {
        BankInit();
        transaction = new Transaction(bank, COMMERCIAL_ACCOUNT, 1111);
    }

    public void BankInit(){
        bank = new Bank();
        Company company = new Company("Company Test", 1);
        Person person = new Person("Person Name test", "Person Lastname Test", 2);
        COMMERCIAL_ACCOUNT = bank.openCommercialAccount(company, 1111, STARTING_DEPOSIT_COMMERCIAL, person);
        CONSUMER_ACCOUNT = bank.openConsumerAccount(person, 2222, STARTING_DEPOSIT_CONSUMER);
    }

    @Test
    public void getBalance() throws Exception {
        TransactionInit();

        assertEquals("Validating transaction balance", STARTING_DEPOSIT_COMMERCIAL, transaction.getBalance(), 0);
    }

    @Test
    public void badAuthenticationInTransaction() throws Exception {
        BankInit();
        try {
            transaction = new Transaction(bank, COMMERCIAL_ACCOUNT, 4444);
        } catch (Exception e) {
            assertEquals("Testing invalid PIN for transaction.", "PIN number for given account is incorrect.", e.getMessage());
        }
    }

    @Test
    public void credit() throws Exception {
        TransactionInit();

        assertEquals("Validating transaction balance - BEFORE credit", STARTING_DEPOSIT_COMMERCIAL, transaction.getBalance(), 0);
        transaction.credit(1000.0);
        assertEquals("Validating transaction balance - AFTER credit", STARTING_DEPOSIT_COMMERCIAL + 1000.0, transaction.getBalance(), 0);
    }

    @Test
    public void debit() throws Exception {
        TransactionInit();

        assertEquals("Validating transaction balance - BEFORE debit", STARTING_DEPOSIT_COMMERCIAL, transaction.getBalance(), 0);
        transaction.debit(1000.0);
        assertEquals("Validating transaction balance - AFTER debit", STARTING_DEPOSIT_COMMERCIAL - 1000.0, transaction.getBalance(), 0);
    }
}