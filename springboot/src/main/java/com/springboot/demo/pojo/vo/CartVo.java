package com.springboot.demo.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author LiZeyuan
 * @date 2022/5/15 13:22
 */
@Data
public class CartVo implements Serializable {
    private Integer id;
    private Integer medicineId;
    private Integer userID;
    private Integer medicineNum;
    private String name;
    private BigDecimal price;
    private String iconPath;

}
