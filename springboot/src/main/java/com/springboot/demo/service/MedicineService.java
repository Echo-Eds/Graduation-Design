package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Medicine;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 16:08
 */
public interface MedicineService extends IService<Medicine> {
    void saveNewMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    void deleteMedicine(long id);
    Page<Medicine> findPage(Integer pageNum, Integer pageSize, String search);

    List<Medicine> getMedicineType();
    List<Medicine> getAllMedicine();
    List<Medicine> selectByType(String Type);
    Object getDetails(Integer id);
    List<Medicine> selectBySearch(String Search);
}
