package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Professor;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 19:59
 */
public interface ProfessorInfoService extends IService<Professor> {
    Object updateProInfo(Professor professor);
}
