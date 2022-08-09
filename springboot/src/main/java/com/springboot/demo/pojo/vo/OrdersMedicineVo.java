package com.springboot.demo.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiZeyuan
 * @date 2022/5/15 13:46
 */
@Data
public class OrdersMedicineVo {
    private Integer medicineId;
    private String name;
    private BigDecimal price;
    private String iconPath;
    private String type;
    private Integer medicineNum;
    private Date time;
}
