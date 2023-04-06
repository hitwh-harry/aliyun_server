package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.AdminMapper;
import com.chinahitech.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    public Page<User> findAll(Page<User> page) {
        return userMapper.selectPage(page,null);
    }

    public Boolean loginConfirm(String username, String password) {
        String pass = adminMapper.getPassword(username);
        if (pass != null && pass.equals(password))
            return true;
        else
            return false;
    }

    public Boolean usernameExist(String username) {
        return (adminMapper.getPassword(username) != null);
    }
}
