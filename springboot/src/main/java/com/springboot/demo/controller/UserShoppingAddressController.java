package com.springboot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.UserShoppingAddressMapper;
import com.springboot.demo.pojo.User;
import com.springboot.demo.pojo.UserShoppingAddress;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/17 15:26
 * 我的收货地址管理
 */
@RestController
@RequestMapping("/address")
public class UserShoppingAddressController {
    @Resource
    UserShoppingAddressMapper userShoppingAddressMapper;

    /**
     * 插入新数据（带userId插入）
     * @param userShoppingAddress
     * @return
     */
    @PostMapping
    public Result<?> save(@RequestBody UserShoppingAddress userShoppingAddress) {

        userShoppingAddressMapper.insert(userShoppingAddress);
        return Result.success();
    }

    /**
     * 此处只get当前用户的记录，但是所有的记录都在一张表里，userId为user表的外键
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam Integer userId
    ) {

        //hutool插件
        LambdaQueryWrapper<UserShoppingAddress> wrapper = Wrappers.<UserShoppingAddress>lambdaQuery();
        //查询所有id为当前用户的收货地址
        Page<UserShoppingAddress> userShoppingAddressPage =
                userShoppingAddressMapper.selectPage(new Page<>(pageNum, pageSize),
                        wrapper.eq(UserShoppingAddress::getUserid, userId));
        return Result.success(userShoppingAddressPage);
    }

    /**
     * 前端传过来的接口有主键id，userId,这个是根据主键id(addressid)进行修改
     * @param userShoppingAddress
     * @return
     */
    @PutMapping
    public Result<?> update(@RequestBody UserShoppingAddress userShoppingAddress) {
        userShoppingAddressMapper.updateById(userShoppingAddress);
        return Result.success();
    }


    @DeleteMapping("/{addressid}")
    public Result<?> delete(@PathVariable long addressid) {
        userShoppingAddressMapper.deleteById(addressid);
        return Result.success();
    }

    @GetMapping("/addressToOrder/{uid}")
    public Result<?> addressToOrder(@PathVariable String uid) {
        QueryWrapper<UserShoppingAddress> wrapper = new QueryWrapper<>();
        wrapper.select("address").eq("userid",uid);
        List<UserShoppingAddress> list = userShoppingAddressMapper.selectList(wrapper);
        return Result.success(list);
    }
}
