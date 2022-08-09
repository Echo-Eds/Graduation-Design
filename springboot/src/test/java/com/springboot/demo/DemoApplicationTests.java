package com.springboot.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.demo.mapper.ProfessorMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.pojo.SkillsTraining;
import com.springboot.demo.pojo.User;
import com.springboot.demo.service.SkillsTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    UserMapper userMapper;
    @Resource
    ProfessorMapper professorMapper;

    @Resource
    SkillsTrainingService skillsTrainingService;
//    @Test
//    public void selectByWrapper10() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("username", "age");
//        List<User> users = userMapper.selectList(queryWrapper);
//        users.forEach(System.out::println);
//    }
//
//    @Test
//    public void selectByWrapper11() {
//        QueryWrapper<Professor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("age");
//        List<Professor> professors = professorMapper.selectList(queryWrapper);
//        System.out.println("==============");
//        professors.forEach(System.out::println);
//    }
    @Test
    public void selectType() {
        QueryWrapper<SkillsTraining> wrapper = new QueryWrapper<>();
        wrapper.select("training_title");
        BaseMapper<SkillsTraining> baseMapper = skillsTrainingService.getBaseMapper();

        System.out.println(baseMapper.selectList(wrapper));
    }

    @Test
    public void testDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
    }
}
