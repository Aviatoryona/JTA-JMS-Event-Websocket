package com.internship.jta.action;

import com.internship.jta.beans.Credit;
import com.internship.jta.beans.Debit;
import com.internship.jta.beans.MyBeanI;
import com.internship.jta.model.BankAccount;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class BeanService extends HttpServlet {
    @EJB
    MyBeanI myBeanI;
    @Inject
            @Debit
    Event<BankAccount> debitBankAccountEvent;
    @Inject
    @Credit
    Event<BankAccount> creditBankAccountEvent;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
