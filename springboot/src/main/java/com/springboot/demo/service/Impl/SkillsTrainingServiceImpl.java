package com.springboot.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.SkillsTrainingMapper;
import com.springboot.demo.pojo.SkillsTraining;
import com.springboot.demo.service.SkillsTrainingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 16:48
 */
@Service("SkillsTrainingServiceImpl")
public class SkillsTrainingServiceImpl extends ServiceImpl<SkillsTrainingMapper, SkillsTraining>
        implements SkillsTrainingService{
    @Override
    public List<SkillsTraining> getType() {
        return baseMapper.getType();
    }

    @Override
    public List<SkillsTraining> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public List<SkillsTraining> selectByType(String Type) {
        LambdaQueryWrapper<SkillsTraining> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SkillsTraining::getTrainingType,Type);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<SkillsTraining> selectSearch(String Search) {
        LambdaQueryWrapper<SkillsTraining> wrapper = Wrappers.lambdaQuery();
        wrapper.like(SkillsTraining::getTrainingTitle,Search);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Object getDetails(Integer id) {
        LambdaQueryWrapper<SkillsTraining> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SkillsTraining::getId,id);
        return baseMapper.selectOne(wrapper);
    }
}
