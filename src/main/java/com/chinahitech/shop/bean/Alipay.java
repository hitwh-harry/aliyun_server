package com.chinahitech.shop.bean;

/**
 * 支付实体对象
 * 根据支付宝接口协议，其中的属性名，必须使用下划线，不能修改
 */
public class Alipay {

    /**
     * 商户订单号，必填
     */
    private String out_trade_no;
    /**
     * 订单名称，必填
     */
    private String subject;
    /**
     * 付款金额，必填
     * 根据支付宝接口协议，必须使用下划线
     */
    private String total_amount;
    /**
     * 商品描述，可空
     */
    private String body;
    /**
     * 超时时间参数
     */
    private String timeout_express = "10m";
    /**
     * 产品编号
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";


    public Alipay() {
    }

    public Alipay(String out_trade_no, String subject, String total_amount, String body, String timeout_express, String product_code) {
        this.out_trade_no = out_trade_no;
        this.subject = subject;
        this.total_amount = total_amount;
        this.body = body;
        this.timeout_express = timeout_express;
        this.product_code = product_code;
    }

    public void createOut_trade_no(String username) {
        long totalMilliSeconds = System.currentTimeMillis();
        this.out_trade_no = username + String.valueOf(totalMilliSeconds);
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    @Override
    public String toString() {
        return "Alipay{" +
                "out_trade_no='" + out_trade_no + '\'' +
                ", subject='" + subject + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", body='" + body + '\'' +
                ", timeout_express='" + timeout_express + '\'' +
                ", product_code='" + product_code + '\'' +
                '}';
    }
}
