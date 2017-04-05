package com.spr.service;

import com.spr.model.History;
import com.spr.model.Client;
import com.spr.model.User;
import java.util.List;

/**
 * Created by Andreea ADM on 3/29/2017.
 */
public interface HistoryService {

    public List<History> findHistory();
    public List<History> findByUser(Integer id);
    public History findById(Integer id);
    public History createAccount(String userID, String accountIBAN, Integer clientID, String action);
    public History createClient(String userID, Integer clientID, String action);
    public History createTransfer(String userID, String sendIBAN, String receiveIBAN, String action);
    public void deleteAllEmployeeHistory(Integer id);
    public List<History> getReportForPeriod(String data, Integer id);
}
