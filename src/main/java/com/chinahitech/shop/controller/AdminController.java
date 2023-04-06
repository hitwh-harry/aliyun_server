package com.chinahitech.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.service.AdminService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/all")
    public Result getAll(String token, @RequestParam(defaultValue = "1") int pageNum){
        String username = JwtUtils.getClaimsByToken(token).getSubject();

        if(adminService.usernameExist(username)) {//分页对象，传入当前页码及每页的数量
            Page<User> page = new Page(pageNum, 10);

            Page<User> brands = adminService.findAll(page);
            return Result.ok().data("items", brands);
        }
        else return Result.error();
    }

}
