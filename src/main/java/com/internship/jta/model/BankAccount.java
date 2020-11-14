package com.internship.jta.model;

import javax.ejb.Stateful;
import javax.inject.Singleton;

@Singleton
@Stateful
public class BankAccount implements BankAccountI{
    private double balance = 0;
    public void debit(double amount)
    {
        this.balance -= amount;
    }
    public void credit(double amount)
    {
        this.balance += amount;
    }
}
