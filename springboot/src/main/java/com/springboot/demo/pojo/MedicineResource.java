package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author LiZeyuan
 * @date 2022/4/15 19:13
 */
@Data
@TableName("medicine_resource")
public class MedicineResource {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String iconPath;
    private Date uploadTime;
    private String filePath;
}
