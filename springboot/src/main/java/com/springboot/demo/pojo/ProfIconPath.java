package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/4/20 13:24
 */
@Data
@TableName("index_prof_icon")
public class ProfIconPath {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer professorid;
    private String iconPath;
}
