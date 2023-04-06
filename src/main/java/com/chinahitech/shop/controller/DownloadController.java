package com.chinahitech.shop.controller;

import com.chinahitech.shop.service.DownloadService;
import com.chinahitech.shop.service.UserService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @RequestMapping("/getInfo")
    public Result getInfo(@RequestHeader("X-Token")String token) {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        System.out.println("getInfo");
//        System.out.println(username);

        String filename = downloadService.getFileName();
        int times = downloadService.getUserTimes(username);
        return Result.ok().data("filename", filename).data("times", times);
    }


    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("token") String token, HttpServletResponse response) throws Exception {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        System.out.println("downLoadFile");

        int times = downloadService.getUserTimes(username);
        if (times != 0) {
//            downloadService.minusUserTimes(username);
            downloadService.downLoadFile(response);
        }
    }


//    @RequestMapping("/get")
//    public Result getByParentId(Long parentId){
////      根据父ID查询子分类
//        List<Admin> categories = userService.getByParentId(parentId);
//        return Result.ok().data("items",categories);
//    }

}
