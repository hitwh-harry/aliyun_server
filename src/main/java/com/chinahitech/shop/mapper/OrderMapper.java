package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Orders;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Orders> {

    @Select("select stat from orders where username = #{username} and out_trade_no = #{out_trade_no}")
    int getStat(String username,String out_trade_no);

    @Update("update orders set stat=1 where username = #{username} and out_trade_no = #{out_trade_no}")
    void updateStat(String username,String out_trade_no);

}
