package com.zhifei.demo.mapper;

import com.zhifei.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("lisi");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
        System.out.println(user.getUserId());
    }

    @Test
    public void testQueryAll(){
        User user = new User();
        user.setName("zs");
        List<User> userList = userMapper.queryAll();
        System.out.println(userList);
    }
}
