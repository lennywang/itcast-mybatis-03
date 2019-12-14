package com.itcast.test;

import com.itcast.bean.User;
import com.itcast.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 **/
public class MybatisAnnotationTest {


    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    InputStream inputStream;
    IUserDao mapper ;

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testSelect()
    {
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() throws ParseException {
        User user = new User();
        user.setUserName("灵清");
        user.setSex("男");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2016-04-22");
        user.setBirthday(date);
        mapper.insertUser(user);
    }

    @Test
    public void testUpdate() throws ParseException {
        User user = new User();
        user.setId(22);
        user.setUserName("灵清");
        user.setSex("男");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2016-04-22");
        user.setBirthday(date);
        user.setAddress("广东省");
        mapper.updateUser(user);
    }




}
