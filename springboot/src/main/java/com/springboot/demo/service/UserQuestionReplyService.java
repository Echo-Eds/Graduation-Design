package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.UserQuestion;
import com.springboot.demo.pojo.UserQuestionReply;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 21:09
 */
public interface UserQuestionReplyService extends IService<UserQuestionReply> {
    List<UserQuestionReply> getRep(Integer questionID);
    Object addRep(UserQuestionReply userQuestionReply);
    Page<UserQuestionReply> getAllMyRep(Integer professorId, Integer pageNum, Integer pageSize);
    Object deleteMyRep(Integer id);
}
