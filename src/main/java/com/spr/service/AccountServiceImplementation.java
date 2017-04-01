package com.spr.service;

import com.spr.model.Account;
import com.spr.repository.AccountRepository;
import com.spr.exception.AccountNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Service
public class AccountServiceImplementation implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Account create(Account account) {
        Account createdAccount = account;

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        return accountRepository.save(createdAccount);
    }

    @Override
    @Transactional(rollbackFor=AccountNotFound.class)
    public Account update(Account account) throws AccountNotFound {
        Account updatedAccount = accountRepository.findOne(account.getId());
        if (updatedAccount == null)
            throw new AccountNotFound();
        updatedAccount.setAmount(account.getAmount());
        updatedAccount.setTypeAccount(account.getTypeAccount());
        return updatedAccount;
    }

    @Override
    @Transactional(rollbackFor=AccountNotFound.class)
    public Account updateAmount(Integer id, Double value) throws AccountNotFound{
        Account updatedAccount = accountRepository.findOne(id);
        if (updatedAccount == null)
            throw new AccountNotFound();
        updatedAccount.setAmount(updatedAccount.getAmount() - value);
        return updatedAccount;
    }


    @Override
    @Transactional(rollbackFor=AccountNotFound.class)
    public Account delete(Integer Id) throws AccountNotFound {
        Account deletedAccount = accountRepository.findOne(Id);
        if (deletedAccount == null)
            throw new AccountNotFound();
        accountRepository.delete(deletedAccount);
        return deletedAccount;
    }

    @Override
    @Transactional
    public Account findById(Integer Id) {
        return accountRepository.findOne(Id);
    }

    @Override
    @Transactional
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account transferMoney(Integer id1, Integer id2, Double value) {
        Account sender = accountRepository.findOne(id1);
        Account receiver = accountRepository.findOne(id2);


        return sender;
    }
}
