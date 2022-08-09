package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.MedicineResource;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 14:41
 */
public interface MedicalResourceService extends IService<MedicineResource> {
    Object saveRes(MedicineResource medicineResource);
    Object updateRes(MedicineResource medicineResource);
    Integer deleteByID(long id);
    Page<MedicineResource> findPage(Integer pageNum, Integer pageSize, String search);
    List<MedicineResource> getAll();
    List<MedicineResource> getType();
    List<MedicineResource> selectByType(String Type);
    List<MedicineResource> selectBySearch(String Search);
}
