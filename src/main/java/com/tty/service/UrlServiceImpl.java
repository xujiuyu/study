package com.tty.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tty.entity.Url;
import com.tty.entity.User;
import com.tty.mapper.UrlMapper;
import com.tty.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    UrlMapper urlMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean insert(Url url) {
        return urlMapper.insert(url) > 0;
    }

    @Override
    public Url getUrlByShortUrlId(String shortUrlId) {
        LambdaQueryWrapper<Url> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Url::getShortUrlId, shortUrlId);
        return urlMapper.selectOne(queryWrapper);
    }

    @Override
    public Url getUrlByOrignUrl(String originUrl) {
        LambdaQueryWrapper<Url> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Url::getOriginUrl, originUrl);
        return urlMapper.selectOne(queryWrapper);
    }


}
