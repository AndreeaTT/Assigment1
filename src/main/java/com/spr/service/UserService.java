package com.spr.service;

import java.util.Map;
import com.spr.exception.UserNotFound;
import com.spr.model.User;
import java.util.List;
import java.sql.SQLException;

/**
 * Created by Andreea ADM on 3/27/2017.
 */
public interface UserService {

    public User create(User user);
    public User update(User user) throws UserNotFound;
    public User delete(Integer userID)throws UserNotFound;
    public User findById(Integer id);
    public List<User> findAllUsers();
    public User findByUsername(String username);
}
