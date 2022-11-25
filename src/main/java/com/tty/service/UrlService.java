package com.tty.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tty.entity.Url;

public interface UrlService{
    Boolean insert(Url url);
    Url getUrlByShortUrlId(String shortUrlId);
    Url getUrlByOrignUrl(String originUrl);
}
