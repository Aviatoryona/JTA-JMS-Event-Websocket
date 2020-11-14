package com.internship.jta.beans;

import com.internship.jta.model.BankAccount;

import javax.ejb.Local;

@Local
public interface MyBeanI {
    void transact(BankAccount debit, BankAccount credit, double amount);
}
