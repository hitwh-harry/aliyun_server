package com.chinahitech.shop.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TablesMapper {

    @Select("select * from ${table}")
    List<Map<String,Object>> getAll(String table);
}
