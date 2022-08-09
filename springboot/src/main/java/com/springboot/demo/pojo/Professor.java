package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/3/10 11:39
 */
@TableName("professor")
@Data
public class Professor {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String age;
    private String sex;
    private String department;
    private String iconPath;
}
