package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/4/13 20:43
 */
@TableName("user_icon")
@Data
public class UserIcon {
    @TableId(value = "icon_id")
    private Integer iconId;
    private Integer id;
    private String userIcon;
}
