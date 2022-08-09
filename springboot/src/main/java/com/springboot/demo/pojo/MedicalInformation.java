package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author LiZeyuan
 * @date 2022/5/12 21:28
 */
@Data
@TableName("medical_information")
public class MedicalInformation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String professorId;
    private String content;
    private Date time;
    private String author;
}
