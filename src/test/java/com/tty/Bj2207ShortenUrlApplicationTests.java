package com.tty;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tty.entity.Url;
import com.tty.entity.User;
import com.tty.mapper.UrlMapper;
import com.tty.mapper.UserMapper;
import com.tty.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SpringBootTest
class Bj2207ShortenUrlApplicationTests {

    @Autowired
    UrlMapper urlMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

//    @Test
//    void testUrlMapper() {
//        Url url = new Url(null, 1594500946021933057L, "https://www.runoob.com/git/git-commit.html", "adminurl01", null, null);
//        urlMapper.insert(url);
//    }

    @Test
    void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void testUserService() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, 1594500946021933057L);
        List<User> users = userService.getAllUser(wrapper);
        System.out.println(users);
    }

    @Test
    void testLogin() {
        User admin = userService.login("admin", "1234");
        System.out.println(admin);
    }

}
