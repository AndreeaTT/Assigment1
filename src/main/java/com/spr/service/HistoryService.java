package com.spr.service;

import com.spr.model.History;
import com.spr.model.Client;
import com.spr.model.User;
import java.util.List;

/**
 * Created by Andreea ADM on 3/29/2017.
 */
public interface HistoryService {

    public History create(History history, Integer userID, String action);
    public List<History> findHistory();
    public List<History> findByUser(Integer id);
}
