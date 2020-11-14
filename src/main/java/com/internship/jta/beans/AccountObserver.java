package com.internship.jta.beans;

import com.internship.jta.model.BankAccount;

import javax.enterprise.event.Observes;

public class AccountObserver {
    public void debit(@Observes @Debit BankAccount bankAccount)
    {
        bankAccount.debit(0);
    }
    public void credit(@Observes @Credit BankAccount bankAccount)
    {
        bankAccount.credit(0);
    }
}
