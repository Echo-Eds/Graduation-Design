package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/4/20 14:08
 */
@Data
@TableName("index_medicineresource_icon")
public class MedicineResourceIconParth {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer medicineresourceid;
    private String iconPath;
}
