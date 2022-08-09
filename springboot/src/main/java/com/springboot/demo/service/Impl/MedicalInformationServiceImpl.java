package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.MedicalInformationMapper;
import com.springboot.demo.pojo.MedicalInformation;
import com.springboot.demo.pojo.UserQuestion;
import com.springboot.demo.service.MedicalInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 21:38
 */
@Service("MedicalInformationServiceImpl")
public class MedicalInformationServiceImpl extends ServiceImpl<MedicalInformationMapper, MedicalInformation>
        implements MedicalInformationService {
    @Override
    public Page<MedicalInformation> getList(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<MedicalInformation> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(MedicalInformation::getTitle,search);
        }
        return baseMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }

    @Override
    public Object getContent(Integer id) {
        LambdaQueryWrapper<MedicalInformation> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MedicalInformation::getId,id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void insertContent(MedicalInformation medicalInformation) {
        baseMapper.insert(medicalInformation);
    }
}
