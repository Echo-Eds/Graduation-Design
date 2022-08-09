package com.springboot.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.ProfessorDetailsMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.pojo.ProfessorDetails;
import com.springboot.demo.service.ProfessorDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/24 16:37
 */
@Service("professorDetailsService")
public class ProfessorDetailsServiceImpl extends ServiceImpl<ProfessorDetailsMapper, ProfessorDetails>
        implements ProfessorDetailsService {

    /**
     * 分来查看的时候查询当前所有的类型
     * @return
     */
    @Override
    public List<ProfessorDetails> getProfessorType() {
//        LambdaQueryWrapper<ProfessorDetails> wrapper = Wrappers.lambdaQuery();
////        wrapper.select("professor_type")
        QueryWrapper<ProfessorDetails> wrapper = new QueryWrapper<>();
        wrapper.select("distinct professor_type");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ProfessorDetails> getProfessorDepartment() {
        QueryWrapper<ProfessorDetails> wrapper = new QueryWrapper<>();
        wrapper.select("distinct professor_department");
        return baseMapper.selectList(wrapper);
    }

    /**
     * 专家详情页返回专家介绍信息
     * 根据ID查
     * @param id
     * @return
     */
    @Override
    public Object getProfessorDetails(Integer id) {
        LambdaQueryWrapper<ProfessorDetails> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ProfessorDetails::getId,id);
        return baseMapper.selectOne(wrapper);
    }


}
