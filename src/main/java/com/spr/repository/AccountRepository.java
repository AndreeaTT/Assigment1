package com.spr.repository;

import com.spr.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * Created by Andreea ADM on 3/26/2017.
 */

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByClientID(Integer clientID);
    Account findByIban(String iban);
    List<Account> findByClientIDAndAmountGreaterThan(Integer clientID, Double amount);
}
