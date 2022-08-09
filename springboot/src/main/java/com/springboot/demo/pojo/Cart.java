package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/5/14 19:03
 */
@Data
@TableName("cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer medicineId;
    private Integer userId;
    private Integer medicineNum;
}
