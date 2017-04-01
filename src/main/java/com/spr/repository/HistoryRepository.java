package com.spr.repository;

import com.spr.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by Andreea ADM on 3/29/2017.
 */

public interface HistoryRepository extends JpaRepository<History, Integer>{

    List<History> findByUserID(Integer userID);
}
