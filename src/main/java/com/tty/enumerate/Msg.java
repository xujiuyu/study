package com.tty.enumerate;

/**
 * @ClassName Msg
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/22 上午 10:43
 **/
public enum Msg {

    LOGIN_SUC("登录成功"),
    LOGIN_FAIL("登录失败"),
    REG_SUC("注册成功"),
    CREATE_SUC("创建成功"),
    CREATE_FAIL("创建失败");

    private String text;

    Msg(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
