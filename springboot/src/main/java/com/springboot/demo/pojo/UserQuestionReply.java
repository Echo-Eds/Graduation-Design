package com.springboot.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author LiZeyuan
 * @date 2022/5/13 21:07
 */
@Data
@TableName("user_question_reply")
public class UserQuestionReply {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer professorId;
    private Integer questionId;
    private String content;
    private Date time;
    private String professorName;
}
