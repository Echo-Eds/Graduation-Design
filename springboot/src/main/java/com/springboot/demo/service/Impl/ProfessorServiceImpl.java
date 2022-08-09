package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.ProfessorMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.service.ProfessorDetailsService;
import com.springboot.demo.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家信息查询所有专家，分类查询，等等复杂条件
 * @author LiZeyuan
 * @date 2022/4/26 14:58
 */
@Service("ProfessorServiceImpl")
public class ProfessorServiceImpl extends ServiceImpl<ProfessorMapper, Professor> implements ProfessorService {

    @Override
    public List<Professor> getAll() {
//        QueryWrapper<Professor> wrapper = new QueryWrapper<>();
        return baseMapper.selectAll();
    }

    @Override
    public List<Professor> listProfessorType(String professorType) {
        return baseMapper.selectProfessorType(professorType);
    }

    @Override
    public List<Professor> listProfessorDepartment(String professorDepartment) {
        return baseMapper.selectProfessorDepartment(professorDepartment);
    }

    /**
     * 模糊查询专家
     * @param search
     * @return
     */
    @Override
    public List<Professor> searchProfessor(String search) {
        LambdaQueryWrapper<Professor> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Professor::getName,search);
        }
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Object selectOne(Integer id) {
        LambdaQueryWrapper<Professor> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Professor::getId, id);
        return baseMapper.selectOne(wrapper);
    }


}
