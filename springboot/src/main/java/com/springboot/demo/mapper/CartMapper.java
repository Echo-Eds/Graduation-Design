package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.pojo.Cart;

import com.springboot.demo.pojo.vo.CartManagementVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 19:09
 */
public interface CartMapper extends BaseMapper<Cart> {
    List<Cart> selectMyCart(@Param("userId") Integer map);

    @Update("update cart set medicine_num = #{medicineNum} where medicine_id = #{medicineId} and user_id = #{userId}")
    void updateNum(@Param("medicineNum") Integer medicineNum,
                   @Param("medicineId") Integer medicineId,
                   @Param("userId") Integer userId);

    Page<CartManagementVo> selectAll();
}
