package com.ggw.travel.service.impl;

import com.ggw.travel.dao.UserMapper;
import com.ggw.travel.entity.User;
import com.ggw.travel.entity.exception.UserException;
import com.ggw.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        if (userMapper.getByUsername(user.getUsername()) != null) {
            throw new UserException("Username has been used");
        } else {
            userMapper.addUser(user);
        }
    }

    @Override
    public User login(User user) {
        User userDB = userMapper.getByUsername(user.getUsername());
        if (userDB == null) {
            throw new UserException("Username does not exist.");
        } else if (!user.getPassword().equals(userDB.getPassword())) {
            throw new UserException("Password is not correct");
        } else {
            return userDB;
        }
    }
}
