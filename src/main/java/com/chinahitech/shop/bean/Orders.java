package com.chinahitech.shop.bean;

public class Orders {
    private String username;
    private String out_trade_no;
    private int stat;

    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", status=" + stat +
                '}';
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
