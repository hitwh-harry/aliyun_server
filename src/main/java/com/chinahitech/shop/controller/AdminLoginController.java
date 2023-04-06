package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.Admin;
import com.chinahitech.shop.service.AdminService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/login")
@CrossOrigin
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/click")
    public Result login(@RequestBody Admin admin) {
        System.out.println("login");
        if (adminService.loginConfirm(admin.getUsername(), admin.getPassword())) {
            String token = JwtUtils.generateToken(admin.getUsername());
            return Result.ok().data("token", token);
        } else
            return Result.error();
    }

    @GetMapping("/info")  // "token:xxx"
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://bpic.51yuansu.com/pic3/cover/01/08/77/590314553c540_610.jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        return Result.ok();
    }

}
