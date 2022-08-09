package com.springboot.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mapper.OrdersMedicineMapper;
import com.springboot.demo.pojo.Orders;
import com.springboot.demo.pojo.OrdersMedicine;
import com.springboot.demo.service.OrdersMedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:32
 */
@Service("OrdersMedicineServiceImpl")
public class OrdersMedicineServiceImpl extends ServiceImpl<OrdersMedicineMapper, OrdersMedicine>
        implements OrdersMedicineService {

    @Override
    public void addOrdersMedicine(Integer orderNo, Integer medicineId, Integer medicineNum) {
        OrdersMedicine ordersMedicine = new OrdersMedicine();
        ordersMedicine.setOrderNo(orderNo);
        ordersMedicine.setMedicineId(medicineId);
        ordersMedicine.setMedicineNum(medicineNum);
        baseMapper.insert(ordersMedicine);
    }

    @Override
    public List<OrdersMedicine> getOrderMedicineList(Integer orderNo) {
        return baseMapper.getOrderMedicineList(orderNo);
    }

    @Override
    public Orders getOrderStatus(Integer orderNo) {
        return baseMapper.getOrderStatus(orderNo);
    }


}
