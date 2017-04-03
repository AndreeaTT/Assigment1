package com.spr.service;

import com.spr.model.History;
import com.spr.model.User;
import com.spr.model.Client;
import com.spr.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Created by Andreea ADM on 3/29/2017.
 */

@Service
public class HistoryServiceImplementation implements HistoryService{

    @Resource
    private HistoryRepository historyRepository;

    @Override
    @Transactional
    public History createAccount(Integer userID, Integer accountID, Integer clientID, String action){
        History history = new History();

        String description;

        if (action.contains("transfer"))
            description = action + " " + accountID.toString() + " " + "for client with ID: " + clientID.toString();
        else
            description = action + " " + accountID.toString() + " " + "to account with ID " + clientID.toString();

        history.setUserID(userID);
        history.setAction(description);

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatDate.format(currentDate);
        history.setActionData(date);
        return historyRepository.save(history);
    }

    @Override
    @Transactional
    public History createClient(Integer userID, Integer clientID, String action){
        History history = new History();

        String description = action + " " + clientID.toString();
        history.setUserID(userID);
        history.setAction(description);

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatDate.format(currentDate);
        history.setActionData(date);

        return historyRepository.save(history);

    }

    @Override
    @Transactional
    public List<History> findByUser(Integer id) {
        return historyRepository.findByUserID(id);
    }

    @Override
    @Transactional
    public List<History> findHistory(){
        return historyRepository.findAll();
    }

    @Override
    @Transactional
    public History findById(Integer id){
        return historyRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteAllEmployeeHistory(Integer id){
            List<History> historyList = historyRepository.findByUserID(id);
            for (History history: historyList)
                historyRepository.delete(history);

    }
}
