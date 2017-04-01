package com.spr.service;

import com.spr.model.Transfer;
import java.util.List;

/**
 * Created by Andreea ADM on 4/1/2017.
 */
public interface TransferService {

    public Transfer create(Transfer transfer);
    public List<Transfer> findAll();
    public Transfer findById(Integer id);
    public void transferMoney(Transfer transfer);
}
