package com.tty.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tty.annotation.SysLog;
import com.tty.entity.User;
import com.tty.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 09:37
 **/

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper mapper;

    @SysLog("业务层: 取得所有用户")
    @Override
    public List<User> getAllUser(Wrapper wrapper) {
        return mapper.selectList(wrapper);
    }

    @SysLog("业务层: 登录校验")
    @Override
    public User login(String username, String password) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.eq(User::getUsername, username)
                    .eq(User::getPassword, password);

        return mapper.selectOne(lqw);
    }

    @SysLog("业务层: 注册")
    @Override
    public Boolean register(User user) {
        int affected = mapper.insert(user);
        return affected > 0;
    }

    @Override
    public User getUserBySeparate(String separate) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getSeparate,separate);
        return mapper.selectOne(lqw);
    }
}
