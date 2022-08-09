package com.springboot.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.UserIconMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.pojo.User;
import com.springboot.demo.pojo.UserIcon;
import com.springboot.demo.pojo.UserQuestion;
import com.springboot.demo.service.UserCollectionService;
import com.springboot.demo.service.UserQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/11 15:56
 * 我的信息
 */

@RestController
@RequestMapping("/myinfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9876/", maxAge = 3600)
public class MyInfoController {
    @Resource
    UserMapper userMapper;

    private final UserQuestionService userQuestionService;
    //用户更新个人信息接口
    @PutMapping("/updatemyinfo")
    public Result<?> updateMyInfo(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success(user);
    }

    /**
     * 获取发布当前用户发布信息的列表
     * @param id
     * @return
     */
    @GetMapping("/myLssue/{id}")
    public Result<?> getMyLssue(@PathVariable Integer id) {
        List<UserQuestion> myLssue = userQuestionService.getMyLssue(id);
        return Result.success(myLssue);
    }

    /*
    删除一条发布数据
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> deleteMyLssue(@PathVariable Integer id) {
        userQuestionService.deleteMylssue(id);
        return Result.success();
    }
}
