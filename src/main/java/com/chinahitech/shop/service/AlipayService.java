package com.chinahitech.shop.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.chinahitech.shop.bean.Alipay;
import com.chinahitech.shop.bean.Orders;
import com.chinahitech.shop.config.AlipayConfig;
import com.chinahitech.shop.mapper.OrderMapper;
import com.chinahitech.shop.mapper.UserMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlipayService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    @Value("${unit_price}")
    private int unit_price;

    public String pay(Alipay alipayBean) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                "json",
                alipayConfig.getCharset(),
                alipayConfig.getAppPublicKey(),
                alipayConfig.getSignType()
        );

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }

    //返回购买的次数
    public int confirmOrder(String out_trade_no, String trade_no) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                "json",
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType());

        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");
        AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);

//        System.out.println(response.getTradeStatus()); //TRADE_SUCCESS
//        System.out.println(response.getTotalAmount());

        if (response.getTradeStatus().equals("TRADE_SUCCESS")) {
            double money = Double.parseDouble(response.getTotalAmount());
            return (int) money / unit_price;
        } else return 0;
    }

    //确认同一订单数据库只更新一次times
    public Boolean confirmDatabase(String username, String out_trade_no) {
        if (orderMapper.getStat(username, out_trade_no) == 0) {
            orderMapper.updateStat(username, out_trade_no);
            return true;
        }
        return false;
    }


    public void addTimes(int times, String username) {
        userMapper.updateTimes(times, username);
    }

    public void createOrders(String username, String out_trade_no) {
        Orders orders = new Orders();
        orders.setUsername(username);
        orders.setOut_trade_no(out_trade_no);
        orders.setStat(0);
        orderMapper.insert(orders);
    }

}
