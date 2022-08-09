package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.mapper.SkillsTrainingMapper;
import com.springboot.demo.pojo.SkillsTraining;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 16:47
 */
public interface SkillsTrainingService extends IService<SkillsTraining> {
    List<SkillsTraining> getType();
    List<SkillsTraining> getAll();
    List<SkillsTraining> selectByType(String Type);
    List<SkillsTraining> selectSearch(String Search);
    Object getDetails(Integer id);
}
