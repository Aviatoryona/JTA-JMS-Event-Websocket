package com.internship.jta.beans;

import com.internship.jta.model.BankAccount;
import com.internship.jta.model.BankAccountI;

import javax.ejb.*;

@Stateless
public class MyBean implements MyBeanI{
    @EJB
    BankAccountI bankAccount;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // MUST START A TRANSACTION (T1)
    @Override
    public void transact(BankAccount debit, BankAccount credit, double amount) {
        this.debit(debit, amount); // if this succeeds
        this.credit(credit, amount); // and this fails
        //the whole transaction is lost
    }
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    private void debit(BankAccount bankAccount, double amount) {
        bankAccount.debit(amount);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    private void credit(BankAccount bankAccount, double amount) {
        bankAccount.credit(amount);
    }
}
