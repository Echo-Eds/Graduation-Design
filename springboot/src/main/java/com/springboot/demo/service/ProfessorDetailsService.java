package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.ProfessorDetails;

import java.util.List;

/**
 * ProfessorDetailsService && ProfessorService
 * 这两个都是专家页面的service逻辑，查询的表不一样用了不同的名字
 * @author LiZeyuan
 * @date 2022/4/24 16:36
 */
public interface ProfessorDetailsService extends IService<ProfessorDetails> {
    List<ProfessorDetails> getProfessorType();
    List<ProfessorDetails> getProfessorDepartment();
    Object getProfessorDetails(Integer id);
}
