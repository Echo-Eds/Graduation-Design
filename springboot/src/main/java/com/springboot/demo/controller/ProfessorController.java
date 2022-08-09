package com.springboot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.ProfessorMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LiZeyuan
 * @date 2022/3/10 10:50
 */
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Resource
    ProfessorMapper professorMapper;

    /**
     * 专家用户登录
     * @param professor
     * @return
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody Professor professor) {
        Professor selectOne = professorMapper.selectOne(Wrappers.<Professor>lambdaQuery().eq(Professor::getName,professor.getName())
                .eq(Professor::getPassword,professor.getPassword()));
        if (selectOne == null) {
            return Result.error("-1", "用户名密码错误");
        }
        return Result.success(selectOne);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody Professor professor) {
        //判断用户名是否重复，因为用户名是唯一的
        Professor selectOne = professorMapper.selectOne(Wrappers.<Professor>lambdaQuery().eq(Professor::getName,professor.getName()));
        if (selectOne != null) {
            return Result.error("-1", "用户名重复，请换一个");
        } else {
            if (professor.getPassword() == null) {
                professor.setPassword("123456");
            }
            professorMapper.insert(professor);
        }
        return Result.success();
    }
    /**
     * 新增
     * @param professor
     * @return
     */
    @PostMapping
    public Result<?> AddProfessor(@RequestBody Professor professor) {
        if (professor.getPassword() == null){
            professor.setPassword("12345678");
        }
        professorMapper.insert(professor);
        return Result.success();
    }
    @PutMapping("/edit")
    public Result<?> edit(@RequestBody Professor professor) {
        professorMapper.updateById(professor);
        return Result.success();
    }
    //delete api
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {
        professorMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> selectProfessor(@RequestParam (defaultValue = "1") Integer pageNum,
                                     @RequestParam (defaultValue = "5") Integer pageSize,
                                     @RequestParam (defaultValue = "") String search) {
        LambdaQueryWrapper<Professor> wrapper = Wrappers.<Professor>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Professor::getName, search);
        }
        Page<Professor> findPage = professorMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(findPage);
    }
}
