package com.spr.service;

import com.spr.exception.BillNotFound;
import com.spr.model.Bill;
import com.spr.repository.AccountRepository;
import com.spr.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import com.spr.model.Account;

/**
 * Created by Andreea ADM on 4/3/2017.
 */

@Service
public class BillServiceImplementation implements BillService{

    @Resource
    private BillRepository billRepository;

    @Override
    @Transactional(rollbackFor=BillNotFound.class)
    public Bill delete(Integer id) throws BillNotFound {
        Bill bill = billRepository.findOne(id);
        if (bill == null)
            throw new BillNotFound();
        billRepository.delete(bill);
        return bill;
    }

    @Override
    @Transactional
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    @Transactional
    public List<Bill> findByClient(Integer id) {
        return billRepository.findByClientID(id);
    }

    @Override
    @Transactional
    public Bill findById(Integer id){
        return billRepository.findOne(id);
    }

    @Override
    @Transactional
    public Account payBill(Bill bill, List<Account> accountList){
        for (Account account: accountList)
            if (account.getAmount() >= bill.getValue()) {
                account.setAmount(account.getAmount() - bill.getValue());
                return account;
            }
        return null;
    }
}
