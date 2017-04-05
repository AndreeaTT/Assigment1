package com.spr.service;

import com.spr.exception.BillNotFound;
import com.spr.model.Account;
import com.spr.model.Bill;
import java.util.List;

/**
 * Created by Andreea ADM on 4/3/2017.
 */
public interface BillService {

    public Bill delete(Integer id) throws BillNotFound;
    public List<Bill> findAll();
    public List<Bill> findByClient(Integer id);
    public Bill findById(Integer id);
    public Account payBill(Bill bill, List<Account> accountList);
}
