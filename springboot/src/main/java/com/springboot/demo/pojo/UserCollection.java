package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/5/5 19:28
 */
@TableName("user_collection")
@Data
public class UserCollection {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer titleId;
    private Integer userId;
}
