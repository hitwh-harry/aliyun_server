package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.service.UserService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    // querystring: username=zhangsan&password=123   User user,String username,String password
    // json: {username:zhangsan,password:123}
    // 如果前端传递的数据是json格式，必须使用对象接收，同时需要添加@RequestBody
    public Result login(@RequestBody User user) {

        if (userService.loginConfirm(user.getUsername(), user.getPassword())) {
            String token = JwtUtils.generateToken(user.getUsername());
            return Result.ok().data("token", token);
        } else
            return Result.error();
    }

    @GetMapping("/info")  // "token:xxx"
    public Result info(@RequestHeader("X-Token")String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://bpic.51yuansu.com/pic3/cover/01/08/77/590314553c540_610.jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        return Result.ok();
    }

    @PostMapping("/register")  //
    public Result register(String username,String password){
        if(userService.usernameExist(username)){
            return Result.ok().data("success","false");
        }
        userService.insertNewUser(username,password);
        return Result.ok().data("success","true");
    }
}
