package com.spr.service;

import com.spr.exception.UserNotFound;
import com.spr.model.User;

public interface SecurityService {

    public User findByUsername(String username) throws UserNotFound;
    public String validLogin(String username, String password) throws UserNotFound;
    public String findRole(String username) throws UserNotFound;
}
