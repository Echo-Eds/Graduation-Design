package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/4/24 16:12
 */
@Data
@TableName("professor_details")
public class ProfessorDetails {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String professorType;
    private Integer professorId;
    private String professorDepartment;
    private String details;
}
