package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/3/1 10:55
 */

@TableName("admin")
@Data
public class Admin {
    @TableId(value = "admin_id")
    private String adminId;
    private String adminPassword;
    private String adminNickname;
}
