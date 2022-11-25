package com.tty.entity;

import com.tty.enumerate.Msg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/22 上午 10:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Object data;
    private Boolean flag;
    private Msg msg;

    public Result(Boolean flag, Msg msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
