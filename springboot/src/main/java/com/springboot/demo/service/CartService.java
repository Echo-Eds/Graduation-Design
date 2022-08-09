package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Cart;
import com.springboot.demo.pojo.vo.CartManagementVo;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 20:47
 */
public interface CartService extends IService<Cart> {
    boolean JudgmentRepetition(Cart cart);
    void addOrUpdateOne(Cart cart);
    List<Cart> selectMyCart(Integer id);
    void updateNum(Integer medicineNum,Integer medicineId,Integer userId);
    void deleteRow(Integer medicineId,Integer uid);

    Page<Cart> selectAll(Integer pageNum,Integer pageSize);
}
