package com.spr.service;

import com.spr.exception.AccountNotFound;
import com.spr.model.Account;
import java.util.List;

/**
 * Created by Andreea ADM on 3/26/2017.
 */
public interface AccountService {

    public Account create(Account account);
    public Account update(Account account) throws AccountNotFound;
    public Account updateAmount(Integer id, Double value) throws AccountNotFound;
    public Account delete(Integer id)throws AccountNotFound;
    public Account findById(Integer id);
    public List<Account> findAccounts();
    public Account transferMoney(Integer id1, Integer id2, Double value);
}
