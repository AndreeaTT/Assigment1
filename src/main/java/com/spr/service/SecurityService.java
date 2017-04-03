package com.spr.service;

import com.spr.model.User;

public interface SecurityService {

     public String findLoggedInUsername();
     public void login(String username, String password);
}
