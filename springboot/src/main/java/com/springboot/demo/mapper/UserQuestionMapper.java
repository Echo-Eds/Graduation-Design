package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.UserQuestion;
import org.apache.ibatis.annotations.Param;

/**
 * @author LiZeyuan
 * @date 2022/5/4 20:02
 */
public interface UserQuestionMapper extends BaseMapper<UserQuestion> {
    Object getContent(@Param("titleId") Integer titleId);
    String getAuthorName(@Param("userId") Integer userId);
}
