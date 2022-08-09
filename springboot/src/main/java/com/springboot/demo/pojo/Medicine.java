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
 * @date 2022/4/15 16:22
 */
@Data
@TableName("medicine_info")
public class Medicine {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String type;
    private Date productionDate;
    private String productionUnit;
    private String iconPath;
}
