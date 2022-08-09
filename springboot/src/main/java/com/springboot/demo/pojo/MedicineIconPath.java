package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 药品图片路径
 * @author LiZeyuan
 * @date 2022/4/20 13:55
 */
@Data
@TableName("index_medicine_icon")
public class MedicineIconPath {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer medicineid;
    private String iconPath;
}
