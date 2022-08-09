package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.Orders;
import com.springboot.demo.pojo.OrdersMedicine;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:31
 */
public interface OrdersMedicineService extends IService<OrdersMedicine> {
    void addOrdersMedicine(Integer orderNo,Integer medicineId,Integer medicineNum);
    List<OrdersMedicine> getOrderMedicineList(Integer orderNo);
    Orders getOrderStatus(Integer orderNo);

}
