package com.spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spr.model.Bill;
import java.util.List;

/**
 * Created by Andreea ADM on 4/3/2017.
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {

    public List<Bill> findByClientID(Integer clientID);
}
