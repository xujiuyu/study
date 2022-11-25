package com.tty.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Url
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 09:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private String originUrl;
    private String shortUrlId;
    private Integer visits;
    private Date lastVisited;

}
