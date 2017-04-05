package com.spr.service;

import com.spr.model.Account;
import com.spr.repository.AccountRepository;
import com.spr.exception.AccountNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatDate.format(currentDate);
        account.setCreateData(date);
        return accountRepository.save(createdAccount);
    }

    @Override
    @Transactional(rollbackFor=AccountNotFound.class)
    public Account update(Account account) throws AccountNotFound {
        Account updatedAccount = accountRepository.findOne(account.getId());
        if (updatedAccount == null)
            throw new AccountNotFound();
        updatedAccount.setClientID(account.getClientID());
        updatedAccount.setAmount(account.getAmount());
        updatedAccount.setTypeAccount(account.getTypeAccount());
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
    public Account findByIban(String iban) {
        return accountRepository.findByIban(iban);
    }

    @Override
    @Transactional
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public List<Account> findByBalanceValue(Integer id, Double balance){
        return accountRepository.findByClientIDAndAmountGreaterThan(id, balance);
    }

}
