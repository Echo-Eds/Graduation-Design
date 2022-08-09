package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.Orders;
import com.springboot.demo.pojo.OrdersMedicine;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/16 13:29
 */
public interface OrdersMedicineMapper extends BaseMapper<OrdersMedicine> {
    List<OrdersMedicine> getOrderMedicineList(@Param("orderNo")Integer orderNo);
    Orders getOrderStatus(@Param("orderId") Integer orderId);
}
