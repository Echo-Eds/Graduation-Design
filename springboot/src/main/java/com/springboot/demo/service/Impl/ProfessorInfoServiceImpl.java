package com.springboot.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.ProfessorMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.service.ProfessorInfoService;
import org.springframework.stereotype.Service;

/**
 * @author LiZeyuan
 * @date 2022/5/13 20:00
 */
@Service
public class ProfessorInfoServiceImpl extends ServiceImpl<ProfessorMapper, Professor>
        implements ProfessorInfoService {

    @Override
    public Object updateProInfo(Professor professor) {
        return baseMapper.updateById(professor);
    }
}
