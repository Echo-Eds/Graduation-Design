package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:22
 */
@Data
@TableName("orders")
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer no;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private BigDecimal totalPrice;
    private String state;
    private String address;
}
