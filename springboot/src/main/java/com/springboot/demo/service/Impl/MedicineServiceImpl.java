package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.MedicineMapper;
import com.springboot.demo.pojo.Medicine;
import com.springboot.demo.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 16:08
 */
@Service("MedicineServiceImpl")
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicineService {

    @Override
    public void saveNewMedicine(Medicine medicine) {
        baseMapper.insert(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        baseMapper.updateById(medicine);
    }

    @Override
    public void deleteMedicine(long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Page<Medicine> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<Medicine> wrapper = Wrappers.<Medicine>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Medicine::getName, search);
        }
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public List<Medicine> getMedicineType() {
        QueryWrapper<Medicine> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT type");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Medicine> getAllMedicine() {
        QueryWrapper<Medicine> wrapper = new QueryWrapper<>();
         wrapper.select("id, name, price, stock, type, production_date, production_unit, icon_path");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Medicine> selectByType(String Type) {
        LambdaQueryWrapper<Medicine> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Medicine::getType,Type);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Object getDetails(Integer id) {
        LambdaQueryWrapper<Medicine> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Medicine::getId,id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Medicine> selectBySearch(String Search) {
        LambdaQueryWrapper<Medicine> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(Search)) {
            wrapper.like(Medicine::getName,Search);
        }
        return baseMapper.selectList(wrapper);
    }
}
