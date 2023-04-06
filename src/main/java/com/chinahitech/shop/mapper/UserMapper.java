package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {


    @Select("select password from user where username = #{username}")
    String getPassword(String username);

    @Select("select times from user where username = #{username}")
    int getTimes(String username);

    @Update("update user set times=times-1 where username = #{username}")
    void minusTimes(String username);

    @Update("update user set times=times+#{times} where username = #{username}")
    void updateTimes(int times, String username);
}
