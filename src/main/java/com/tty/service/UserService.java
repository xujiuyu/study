package com.tty.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.tty.annotation.SysLog;
import com.tty.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 09:36
 **/

public interface UserService {
    List<User> getAllUser(Wrapper<User> wrapper);
    User login(String username, String password);
    Boolean register(User user);
    User getUserBySeparate(String separate);
}
