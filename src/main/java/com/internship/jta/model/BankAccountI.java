package com.internship.jta.model;

import javax.ejb.Local;

@Local
public interface BankAccountI {
    void debit(double amount);
    void credit(double amount);
}
