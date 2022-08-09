package com.springboot.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.UserQuestionReplyMapper;
import com.springboot.demo.pojo.UserQuestionReply;
import com.springboot.demo.service.UserQuestionReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 21:10
 */
@Service("UserQuestionReplyServiceImpl")
public class UserQuestionReplyServiceImpl extends ServiceImpl<UserQuestionReplyMapper, UserQuestionReply>
        implements UserQuestionReplyService {
    @Override
    public List<UserQuestionReply> getRep(Integer questionID) {
        return baseMapper.getRep(questionID);
    }

    @Override
    public Object addRep(UserQuestionReply userQuestionReply) {
        return baseMapper.insert(userQuestionReply);
    }

    @Override
    public Page<UserQuestionReply> getAllMyRep(Integer professorId,Integer pageNum,Integer pageSize) {
        LambdaQueryWrapper<UserQuestionReply> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserQuestionReply::getProfessorId,professorId);
        return baseMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }

    @Override
    public Object deleteMyRep(Integer id) {
        return baseMapper.deleteById(id);
    }
}
