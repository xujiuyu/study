package com.tty.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tty.annotation.SysLog;
import com.tty.constant.UrlConstant;
import com.tty.entity.Result;
import com.tty.entity.Url;
import com.tty.entity.User;
import com.tty.enumerate.Msg;
import com.tty.service.UrlService;
import com.tty.service.UserService;
import com.tty.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;

    @Autowired
    UserService userService;

    @SysLog("控制层: 创建短链接")
    @PostMapping("/createUrl")
    @ResponseBody
    public String createUrl(String longUrl, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String[] parts = longUrl.split("\r|\n");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {

            //通用统计：
            //1.创建短url 同时：
            //  显示原始url最后一次被转换的时间
            //  原始url最后一次被访问的时间


            //使用parts[i] 从tbl_url中找出有没有对应的记录



            if (Objects.isNull(user)) {
                //游客逻辑
                Url url = new Url();
                url.setOriginUrl(parts[i]);
                url.setShortUrlId(UrlUtils.generateSeparate(6));

                //在tbl_url插入新数据
                Boolean res = urlService.insert(url);


                //构建短链接
                if (request.getServerPort() == 80 || request.getServerPort() == 443) {
                    sb.append(String.format("%s://%s/%s/%s<br>", request.getScheme(), request.getServerName(),
                            UrlConstant.TRIAL, url.getShortUrlId()));
                } else {
                    sb.append(String.format("%s://%s:%s/%s/%s<br>", request.getScheme(), request.getServerName(), request.getServerPort(),
                            UrlConstant.TRIAL, url.getShortUrlId()));
                }
                json.put("urlRes", new Result(sb.toString(), res, res ? Msg.CREATE_SUC : Msg.CREATE_FAIL));
            } else {
                //用户逻辑
                Url url = new Url();
                url.setUserId(user.getId());
                url.setOriginUrl(parts[i]);
                url.setShortUrlId(UrlUtils.generateSeparate(6));

                //在tb_url插入新数据
                Boolean res = urlService.insert(url);

                //构建短链接
                if (request.getServerPort() == 80 || request.getServerPort() == 443) {
                    //http://主机名字/前缀/shortUrlId, http://localhost/a3B48/6eIcfh
                    sb.append(String.format("%s://%s/%s/%s<br>", request.getScheme(), request.getServerName(),
                            user.getSeparate(), url.getShortUrlId()));
                } else {

                    //http://主机名字/前缀/shortUrlId, http://localhost:8080/a3B48/6eIcfh
                    sb.append(String.format("%s://%s:%s/%s/%s<br>", request.getScheme(), request.getServerName(), request.getServerPort(),
                            user.getSeparate(), url.getShortUrlId()));
                }
                json.put("urlRes", new Result(sb.toString(), res, res ? Msg.CREATE_SUC : Msg.CREATE_FAIL));
            }
        }
        return json.toJSONString();
    }

    @SysLog("控制层: 以短链接访问")
    @GetMapping("{separate}/{shortUrlId}")
    public String visit(@PathVariable String separate, @PathVariable String shortUrlId, HttpServletResponse response) throws IOException {
        //检查链接是否合法
        //用separate取得User对象
        User user = userService.getUserBySeparate(separate);

        if (Objects.isNull(user) && !UrlConstant.TRIAL.equals(separate)) {
            return "noPage";
        } else {
            //separate合法
            //用shortUrlId 找到原始链接, 并且重定向
            Url url = urlService.getUrlByShortUrlId(shortUrlId);
            if (Objects.isNull(url)) {
                return "noPage";
            } else {
                response.sendRedirect(url.getOriginUrl());
            }
        }
        return "noPage";
    }

}


