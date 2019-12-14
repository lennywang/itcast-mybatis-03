package com.itcast.dao;

import com.itcast.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *
 **/
public interface IUserDao {

    /**
     * 查詢所有用戶
     **/
    @Select(value = "select * from user")
    List<User> findAll();

    @Insert(value = "insert into user(username,sex,birthday,address) values (#{userName},#{sex},#{birthday},#{address})")
    int insertUser(User user);

    @Update(value = "update user set username=#{userName},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    int updateUser(User user);
}
