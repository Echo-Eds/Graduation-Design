package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.Medicine;
import com.springboot.demo.pojo.MedicineResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/1/26 13:51
 */
public interface MedicineResourceMapper extends BaseMapper<MedicineResource> {
    List<MedicineResource> getAll();
    List<MedicineResource> getType();
}
