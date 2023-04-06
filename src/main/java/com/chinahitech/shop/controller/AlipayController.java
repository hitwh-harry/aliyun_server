package com.chinahitech.shop.controller;

import com.alipay.api.AlipayApiException;
import com.chinahitech.shop.bean.Alipay;
import com.chinahitech.shop.service.AlipayService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @PostMapping(value = "/alipay")
    public Result alipay(@RequestHeader("X-Token")String token, String subject, String total_amount, String body) throws AlipayApiException {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        System.out.println("alipay");

        Alipay alipayBean = new Alipay();
        alipayBean.createOut_trade_no(username);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(total_amount);
        alipayBean.setBody(body);

        alipayService.createOrders(username, alipayBean.getOut_trade_no());
        String form = alipayService.pay(alipayBean);

        return Result.ok().data("form", form);
    }

    @PostMapping(value = "/success")
    public Result paySuccess(@RequestHeader("X-Token")String token, String out_trade_no, String trade_no) throws AlipayApiException {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        System.out.println("paySuccess");

        int times = alipayService.confirmOrder(out_trade_no, trade_no);
        Boolean first = alipayService.confirmDatabase(username, out_trade_no);
        if (times != 0 && first) {
            alipayService.addTimes(times, username);
        }

        return Result.ok();
    }
}
