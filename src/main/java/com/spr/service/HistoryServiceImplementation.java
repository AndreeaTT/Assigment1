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

/**
 * Created by Andreea ADM on 3/29/2017.
 */

@Service
public class HistoryServiceImplementation implements HistoryService{

    @Resource
    private HistoryRepository historyRepository;

    @Override
    @Transactional
    public History create(History history, Integer userID, String action){
        history.setUserID(userID);
        history.setAction(action);

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        history.setActionData(date);

        return history;
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
}
