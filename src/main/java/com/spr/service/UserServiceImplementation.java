package com.spr.service;

import com.spr.exception.UserNotFound;
import com.spr.model.User;
import com.spr.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Andreea ADM on 3/27/2017.
 */

@Service("userService")
public class UserServiceImplementation implements UserService{

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        User createdUser = user;
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public User update(User user) throws UserNotFound {
        User updatedUser = userRepository.findOne(user.getId());
        if (updatedUser == null)
            throw new UserNotFound();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRights(user.getRights());
        return updatedUser;
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public User delete(Integer id) throws UserNotFound {
        User deletedUser = userRepository.findOne(id);
        if (deletedUser == null)
            throw new UserNotFound();
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public User findById(Integer Id) {
        return userRepository.findOne(Id);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
