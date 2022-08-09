package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.UserQuestion;

import java.util.List;

/**
 * 询问专家的列表，和详细信息
 * @author LiZeyuan
 * @date 2022/5/4 20:02
 */
public interface UserQuestionService extends IService<UserQuestion> {
    Page<UserQuestion> getQuestionList(Integer pageNum, Integer pageSize, String search);

    Object getContent(Integer titleId);

    String getAuthorName(Integer userId);

    int addContent(UserQuestion userQuestion);

    List<UserQuestion> getMyLssue(Integer id);

    Object deleteMylssue(Integer id);
}
