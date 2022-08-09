package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.UserQuestionMapper;
import com.springboot.demo.pojo.UserQuestion;
import com.springboot.demo.service.UserQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/4 20:04
 */
@Service("UserQuestionServiceImpl")
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionMapper, UserQuestion>
        implements UserQuestionService {
    /**
     * 查询标题和时间 返回给前台渲染
     * @return
     */
    @Override
    public Page<UserQuestion> getQuestionList(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<UserQuestion> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(UserQuestion::getTitle, search);
        }
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    /**
     * 获取详细的数据
     * @param titleId
     * @return
     */
    @Override
    public Object getContent(Integer titleId) {

        return baseMapper.getContent(titleId);
    }

    /**
     * 根据多表查询获取文章的创作用户
     * @param userId
     * @return
     */
    @Override
    public String getAuthorName(Integer userId) {
        return baseMapper.getAuthorName(userId);
    }

    /**
     * 用户提问保存到数据库
     * @param userQuestion
     * @return
     */
    @Override
    public int addContent(UserQuestion userQuestion) {

        return baseMapper.insert(userQuestion);
    }

    /**
     * 查询我所有发布的
     * @param id
     * @return
     */
    @Override
    public List<UserQuestion> getMyLssue(Integer id) {
        LambdaQueryWrapper<UserQuestion> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserQuestion::getUserId,id);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 删除我发布的
     * @param id
     * @return
     */
    @Override
    public Object deleteMylssue(Integer id) {
//        LambdaQueryWrapper<UserQuestion> wrapper = Wrappers.lambdaQuery();
//        wrapper.eq(UserQuestion::getId,id);
        return baseMapper.deleteById(id);
    }
}
