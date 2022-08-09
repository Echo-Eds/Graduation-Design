package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Professor;

import java.util.List;

/**
 * 专家详情界面用来做分类和查询全部数据
 * @author LiZeyuan
 * @date 2022/4/26 14:57
 */
public interface ProfessorService extends IService<Professor> {
    List<Professor> getAll();
    //点击专家类型获取数据
    List<Professor> listProfessorType(String professorType);
    //专家科室获取数据
    List<Professor> listProfessorDepartment(String professorDepartment);
    //搜索专家
    List<Professor> searchProfessor(String search);

    Object selectOne(Integer id);
}
