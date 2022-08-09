package com.springboot.demo.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 此Vo作为后端管理用
 * @author LiZeyuan
 * @date 2022/5/16 21:03
 */
@Data
public class CartManagementVo {
    private Integer id;
    private Integer medicineId;
    private Integer userId;
    private Integer medicineNum;
    private String name;
    private BigDecimal price;
    private String iconPath;
    private String username;
    private Integer age;
    private String type;
}
