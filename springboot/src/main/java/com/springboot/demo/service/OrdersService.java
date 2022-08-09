package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Orders;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:30
 */
public interface OrdersService extends IService<Orders> {
    int addOrder (Integer userId, BigDecimal totalPrice);
    void updateStatus(Integer orderNo,String address);
    Page<Orders> listMyOrders(Integer userId, Integer pageNum, Integer pageSize);
}
