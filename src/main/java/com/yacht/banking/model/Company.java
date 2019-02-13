package com.yacht.banking.model;

import com.yacht.banking.service.AccountHolder;

public class Company extends AccountHolder {

    String companyName;

    public Company(String companyName, int taxid){
        super(taxid);
        this.companyName = companyName;
        this.idNumber = taxid;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
