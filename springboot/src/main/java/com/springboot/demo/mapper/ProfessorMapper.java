package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.Professor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/3/10 12:20
 */
@Mapper
public interface ProfessorMapper extends BaseMapper<Professor> {
    List<Professor> selectAll();

    List<Professor> selectProfessorType(@Param("professorType") String professorType);

    List<Professor> selectProfessorDepartment(@Param("professorDepartment") String professorDepartment);
}
