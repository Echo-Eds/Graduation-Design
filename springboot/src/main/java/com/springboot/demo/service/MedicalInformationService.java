package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.MedicalInformation;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 21:37
 */
public interface MedicalInformationService extends IService<MedicalInformation> {
    Page<MedicalInformation> getList(Integer pageNum, Integer pageSize, String search);
    Object getContent(Integer id);
    void insertContent(MedicalInformation medicalInformation);
}
