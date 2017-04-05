package com.spr.service;

import java.text.ParseException;
import com.spr.model.History;
import com.spr.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public History createAccount(String userID, String accountIBAN, Integer clientID, String action){
        History history = new History();

        String description;
        description = action + " " + accountIBAN + " " + "at client with ID: " + clientID;

        history.setUserID(userService.findByUsername(userID).getId());
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
    public History createTransfer(String userID, String sendIBAN, String receiveIBAN, String action){
        History history = new History();

        String description;
        description = action + " " + sendIBAN + " " + "to account with IBAN: " + receiveIBAN;

        history.setUserID(userService.findByUsername(userID).getId());
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
    public History createClient(String userID, Integer clientID, String action){
        History history = new History();

        String description = action + " " + clientID.toString();
        history.setUserID(userService.findByUsername(userID).getId());
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

    @Override
    @Transactional
    public List<History> getReportForPeriod(String data, Integer id){
        List<History> historyList = historyRepository.findByUserID(id);
        List<History> resultList = new ArrayList<History>();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dataAction;
        java.util.Date compareData;


        for (History history:historyList){
            try {

                dataAction = formatDate.parse(history.getActionData());
                compareData = formatDate.parse(data);
                if (dataAction.compareTo(compareData) >= 0)
                    resultList.add(history);
            }

            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }
}
