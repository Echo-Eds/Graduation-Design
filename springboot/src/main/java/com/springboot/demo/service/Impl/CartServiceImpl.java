package com.springboot.demo.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.CartMapper;
import com.springboot.demo.pojo.Cart;
import com.springboot.demo.pojo.User;
import com.springboot.demo.pojo.vo.CartManagementVo;
import com.springboot.demo.service.CartService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 20:47
 */
@Service("CartServiceImpl")
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    /**
     * 判断加入购物车的时候是否有重复
     * 通过查看数据库的药品id和用户id是否只有记录，
     * 如果有记录，那就把setMedicineNum（getNum + 1)
     * 然后更新selectOne
     * @param cart
     */
    @Override
    public boolean JudgmentRepetition(Cart cart) {
        Integer medicineId = cart.getMedicineId();
        Integer userId = cart.getUserId();
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("medicine_id", medicineId).eq("user_id", userId);
        Cart selectOne = baseMapper.selectOne(wrapper);
        if (selectOne != null) {
            selectOne.setMedicineNum(selectOne.getMedicineNum() + 1);
            addOrUpdateOne(selectOne);
            return true;
        }
        return false;
    }

    @Override
    public void addOrUpdateOne(Cart cart) {

        if (cart.getId() == null) {
            baseMapper.insert(cart);
        } else {
            baseMapper.updateById(cart);
        }
    }

    @Override
    public List<Cart> selectMyCart(Integer id) {
        return baseMapper.selectMyCart(id);
    }

    @Override
    public void updateNum(Integer medicineNum, Integer medicineId,Integer userId) {
        baseMapper.updateNum(medicineNum, medicineId,userId);
    }

    @Override
    public void deleteRow(Integer medicineId,Integer uid) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("medicine_id",medicineId).eq("user_id",uid);
        baseMapper.delete(wrapper);
    }

    @Override
    public Page<Cart> selectAll(Integer pageNum,Integer pageSize) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.select("id ,medicine_id, user_id ,medicine_num");
        return baseMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }

}
