package com.springboot.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.AdminMapper;
import com.springboot.demo.pojo.Admin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LiZeyuan
 * @date 2022/3/7 14:53
 */

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin) {
        Admin selectOne = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminNickname, admin.getAdminNickname())
                .eq(Admin::getAdminPassword, admin.getAdminPassword()));
        if (selectOne == null) {
            return Result.error("-1", "用户名密码错误");
        }
        return Result.success(selectOne);
    }
}