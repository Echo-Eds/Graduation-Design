package com.springboot.demo.service.Impl;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.OrdersMapper;
import com.springboot.demo.pojo.Orders;
import com.springboot.demo.service.OrdersService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:30
 */
@Service("OrdersServiceImpl")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Override
    public int addOrder(Integer userId, BigDecimal totalPrice) {
        //获取订单生成的时间
        Date date = new Date();
        Orders orders = new Orders();
        orders.setTime(date);
        orders.setUserId(userId);
        orders.setTotalPrice(totalPrice);
        orders.setState("未支付");
        baseMapper.insert(orders);
        //查询当前时间的订单号
        return orders.getNo();
//        QueryWrapper<Orders> wrapper = Wrappers.query();
//        wrapper.select("no").eq("time",orders.getTime());
//        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void updateStatus(Integer orderNo,String address) {
        LambdaQueryWrapper<Orders> wrapper = Wrappers.lambdaQuery();
        Orders orders = new Orders();
        orders.setAddress(address);
        orders.setState("已支付");
        wrapper.eq(Orders::getNo,orderNo);
        baseMapper.update(orders,wrapper);
    }

    @Override
    public Page<Orders> listMyOrders(Integer userId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Orders> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Orders::getUserId,userId);
        return baseMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }


}
