package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/4/17 15:01
 */
@Data
@TableName("user_address")
public class UserShoppingAddress {
    @TableId(type = IdType.AUTO)
    private Integer addressid;
    private Integer userid;
    private String address;
    private String name;
    private String phoneNum;
}
