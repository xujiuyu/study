package com.tty.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tty.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 09:03
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
