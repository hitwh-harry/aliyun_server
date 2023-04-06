package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Boolean loginConfirm(String username, String password) {
        String pass = userMapper.getPassword(username);
        if (pass != null && pass.equals(password))
            return true;
        else
            return false;
    }

    public Boolean usernameExist(String username) {
        return !(userMapper.getPassword(username) == null);
    }

    public void insertNewUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTimes(0);
        userMapper.insert(user);
    }
}
