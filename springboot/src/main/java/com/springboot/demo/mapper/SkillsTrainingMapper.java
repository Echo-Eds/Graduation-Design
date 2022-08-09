package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.SkillsTraining;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 16:47
 */
public interface SkillsTrainingMapper extends BaseMapper<SkillsTraining> {
    List<SkillsTraining> getType();
    List<SkillsTraining> getAll();
}
