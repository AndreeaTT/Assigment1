package com.spr.service;

import com.spr.exception.UserNotFound;
import com.spr.model.User;
import com.spr.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * Created by Andreea ADM on 4/4/2017.
 */
@Service
public class SecurityServiceImplementation implements SecurityService{

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public String validLogin(String username, String password) throws UserNotFound{
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFound();
        if (user.getPassword().equals(password)) {
            if (user.getRights().equalsIgnoreCase("Admin"))
                return "LoginAdmin";
            else
                return "LoginEmployee";
        }
        return "WrongPassword";
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public String findRole(String username) throws UserNotFound{
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFound();
        return user.getRights();
    }

}
