package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:24
 */
@Data
@TableName("orders_medicine")
public class OrdersMedicine {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderNo;
    private Integer medicineId;
    private Integer medicineNum;
}
