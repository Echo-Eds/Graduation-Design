package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.UserQuestionReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 21:09
 */
public interface UserQuestionReplyMapper extends BaseMapper<UserQuestionReply> {
    List<UserQuestionReply> getRep(@Param("questionID") Integer questionID);
    List<UserQuestionReply> getAllMyReply(@Param("professorId") Integer professorId);
}
