package com.springboot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;

import com.springboot.demo.mapper.UserMapper;

import com.springboot.demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LiZeyuan
 * @date 2022/1/26 13:28
 */
@RestController
@RequestMapping("/user")
//注解跨域
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST})
public class UserController {

    @Resource
    UserMapper userMapper;

    //login
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User selectOne = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        if (selectOne == null) {
            return Result.error("-1", "用户名密码错误");
        }
        return Result.success(selectOne);
    }

    //register
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        //判断用户名是否重复，因为用户名是唯一的
        User selectOne = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (selectOne != null) {
            return Result.error("-1", "用户名重复，请换一个");
        } else {
            if (user.getPassword() == null) {
                user.setPassword("123456");
            }
            userMapper.insert(user);
        }
        return Result.success();
    }

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        //在后台中给用户设定默认密码
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    //update
    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    //delete api
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    //分页查询接受参数
//    当前页，总页面数，模糊查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        //hutool插件判断serach是否为空,nike_name为null时查询条件
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        Page<User> findPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(findPage);
    }
}
