package com.spr.service;

import com.spr.model.Account;
import com.spr.model.Transfer;
import com.spr.repository.AccountRepository;
import com.spr.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Andreea ADM on 4/1/2017.
 */

@Service
public class TransferServiceImplementation implements TransferService{

    @Resource
    private TransferRepository transferRepository;

    @Resource
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Transfer create(Transfer transfer) {
        Transfer createTransfer = transfer;
        return transferRepository.save(transfer);
    }

    @Override
    @Transactional
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    @Transactional
    public Transfer findById(Integer id) {
        return transferRepository.findOne(id);
    }

    @Override
    @Transactional
    public void transferMoney(Transfer transfer){
        Account accountSender = accountRepository.findOne(transfer.getSenderID());
        Account accountReceiver = accountRepository.findOne(transfer.getReceiverID());
        Double balanceValue = transfer.getValue();

        accountSender.setAmount(accountSender.getAmount() - balanceValue);
        accountReceiver.setAmount(accountReceiver.getAmount() + balanceValue);
    }

}
