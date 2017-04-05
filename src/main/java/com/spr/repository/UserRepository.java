package com.spr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spr.model.User;

/**
 * Created by Andreea ADM on 3/27/2017.
 */

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
    List<User> findByRights(String rights);
}
