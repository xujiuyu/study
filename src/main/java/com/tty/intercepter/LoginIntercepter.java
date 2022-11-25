package com.tty.intercepter;

import com.tty.Bj2207ShortenUrlApplication;
import com.tty.constant.RoleConstant;
import com.tty.entity.Result;
import com.tty.entity.User;
import com.tty.enumerate.Msg;
import com.tty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @ClassName LoginIntercepter
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 10:49
 **/

public class LoginIntercepter implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //游客访问
        if (Objects.isNull(username) && Objects.isNull(password)){
            return true;
        }else {

            //用户或管理员访问
            User user = userService.login(username, password);

            if (!Objects.isNull(user)){
                //登录成功
                HttpSession session = request.getSession();

                session.setAttribute("user", user);
                session.setAttribute("separate",user.getSeparate());

                //判断是管理员还是用户
                if (RoleConstant.ADMIN_ROLE.equals(user.getRole())){
                    //导向管理员页面
                    request.getRequestDispatcher("/admin").forward(request, response);
                }else {
                    return true;
                }

            }else{
                //登录失败
                request.setAttribute("loginRes", new Result(false, Msg.LOGIN_FAIL));
                request.getRequestDispatcher("/").forward(request, response);
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
