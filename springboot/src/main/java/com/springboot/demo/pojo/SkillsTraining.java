package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiZeyuan
 * @date 2022/5/12 16:36
 */
@Data
@TableName("skills_training")
public class SkillsTraining {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String trainingTitle;
    private String trainingType;
    private String trainingDetails;
    private String img;
}
