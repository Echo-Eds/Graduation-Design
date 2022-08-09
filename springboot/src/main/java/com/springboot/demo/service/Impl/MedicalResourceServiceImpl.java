package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.MedicineResourceMapper;
import com.springboot.demo.pojo.MedicineResource;
import com.springboot.demo.service.MedicalResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 14:42
 */
@Service("MedicalResourceServiceImpl")
public class MedicalResourceServiceImpl extends ServiceImpl<MedicineResourceMapper, MedicineResource>
        implements MedicalResourceService {
    @Override
    public Object saveRes(MedicineResource medicineResource) {
        return baseMapper.insert(medicineResource);
    }

    @Override
    public Object updateRes(MedicineResource medicineResource) {
        return baseMapper.updateById(medicineResource);
    }

    @Override
    public Integer deleteByID(long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Page<MedicineResource> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<MedicineResource> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(MedicineResource::getName, search);
        }
        return baseMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }

    @Override
    public List<MedicineResource> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public List<MedicineResource> getType() {
        return baseMapper.getType();
    }

    @Override
    public List<MedicineResource> selectByType(String Type) {
        LambdaQueryWrapper<MedicineResource> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MedicineResource::getType,Type);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<MedicineResource> selectBySearch(String Search) {
        LambdaQueryWrapper<MedicineResource> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(Search)) {
            wrapper.like(MedicineResource::getName,Search);
        }
        return baseMapper.selectList(wrapper);
    }
}
