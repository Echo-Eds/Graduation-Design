package com.springboot.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.Cart;
import com.springboot.demo.pojo.Orders;
import com.springboot.demo.pojo.OrdersMedicine;
import com.springboot.demo.service.CartService;
import com.springboot.demo.service.OrdersMedicineService;
import com.springboot.demo.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/14 20:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final OrdersService ordersService;
    private final OrdersMedicineService ordersMedicineService;

    /**
     * 添加药品到购物车
     *
     * @param cart
     * @return
     */
    @RequestMapping(value = "/addOne", method = RequestMethod.POST)
    public Result<?> addOrUpdateCart(@RequestBody Cart cart) {
        //如果判断重复返回为false
        boolean bool = cartService.JudgmentRepetition(cart);
        if (!bool) {
            try {
                cartService.addOrUpdateOne(cart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }

    /**
     * 查询我购物车所有药品
     *
     * @param id
     * @return
     */
    @GetMapping("/selectMyCart/{id}")
    public Result<?> selectMyCart(@PathVariable Integer id) {
        List<Cart> cartList = cartService.selectMyCart(id);
        return Result.success(cartList);
    }

    /**
     * 购物车里更改药品数量
     *
     * @param num
     * @param id
     * @param uid
     * @return
     */
    @PostMapping("/updateNum/{num}/{id}/{uid}")
    public Result<?> updateNum(@PathVariable Integer num,
                               @PathVariable Integer id,
                               @PathVariable Integer uid) {
        cartService.updateNum(num, id, uid);
        return Result.success();
    }

    /**
     * 删除购物车一行
     *
     * @param id
     * @param uid
     * @return
     */
    @DeleteMapping("/deleteRow/{id}/{uid}")
    public Result<?> deleteRow(@PathVariable Integer id, @PathVariable Integer uid) {
        cartService.deleteRow(id, uid);
        return Result.success();
    }

    /**
     * 创建订单实例，创建订单药品信息实例
     *
     * @param uid
     * @param total
     * @return
     */
    @RequestMapping(value = "/addOrder/{uid}/{total}", method = RequestMethod.POST)
    public Result<?> addOrder(@PathVariable Integer uid, @PathVariable BigDecimal total) {
        int i = ordersService.addOrder(uid, total);
        return Result.success(i);
    }

    @RequestMapping(value = "addOrderMedicine/{no}/{medicineId}/{medicineNum}", method = RequestMethod.POST)
    public Result<?> addOrderMedicine(@PathVariable Integer no,
                                      @PathVariable Integer medicineId,
                                      @PathVariable Integer medicineNum) {
        ordersMedicineService.addOrdersMedicine(no, medicineId, medicineNum);
        return Result.success();
    }

    /**
     * 查询订单中所有商品
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/getOrderMedicineList/{orderNo}")
    public Result<?> getOrderMedicineList(@PathVariable Integer orderNo) {
        List<OrdersMedicine> orderMedicineList = ordersMedicineService.getOrderMedicineList(orderNo);
        return Result.success(orderMedicineList);
    }

    /**
     * 查询订单状态：如：已支付未支付等
     *
     * @param orderNo
     * @return orderStatus
     */
    @GetMapping("/getOrderStatus/{orderNo}")
    public Result<?> getOrderStatus(@PathVariable Integer orderNo) {
        Orders orderStatus = ordersMedicineService.getOrderStatus(orderNo);
        return Result.success(orderStatus);
    }

    /**
     * 下单之后更新订单状态
     *
     * @param no
     * @param address
     * @return
     */
    @GetMapping("/updateStatus/{no}")
    public Result<?> updateStatus(@PathVariable Integer no, @RequestParam(defaultValue = "") String address) {
        try {
            ordersService.updateStatus(no, address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    /**
     * 查询我所有的订单
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllMyOrders")
    public Result<?> selectAllMyOrders(@RequestParam Integer userId,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Orders> ordersPage = ordersService.listMyOrders(userId, pageNum, pageSize);
        return Result.success(ordersPage);
    }

    @GetMapping("/getAll")
    public Result<?> getAll(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Cart> cartPage = cartService.selectAll(pageNum, pageSize);
        return Result.success(cartPage);

    }
}
