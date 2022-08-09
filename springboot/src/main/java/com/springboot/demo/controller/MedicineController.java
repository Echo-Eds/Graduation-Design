package com.springboot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.MedicineMapper;
import com.springboot.demo.pojo.Medicine;
import com.springboot.demo.pojo.User;
import com.springboot.demo.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/medicine")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    /**
     * 后台
     * @author LiZeyuan
     * @date 2022/4/15 16:34
     */
    @PostMapping
    public Result<?> save(@RequestBody Medicine medicine) {
        medicineService.saveNewMedicine(medicine);
        return Result.success();
    }

    @PutMapping("/edit")
    public Result<?> update(@RequestBody Medicine medicine) {
        medicineService.updateMedicine(medicine);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {
        medicineService.deleteMedicine(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page<Medicine> page = medicineService.findPage(pageNum, pageSize, search);
        return Result.success(page);
    }

    /*
    * 前端部分
     */

    /**
     * 查询药品种类
     * @return
     */
    @GetMapping("/type")
    public Result<?> getMedicineType() {
        List<Medicine> medicineType = medicineService.getMedicineType();
        return Result.success(medicineType);
    }

    @GetMapping("/getAll")
    public Result<?> getAllMedicine() {
        List<Medicine> allMedicine = medicineService.getAllMedicine();
        return Result.success(allMedicine);
    }

    @GetMapping("/selectByType")
    public Result<?> selectByType(@RequestParam String Type) {
        List<Medicine> selectByType = medicineService.selectByType(Type);
        return Result.success(selectByType);
    }

    @GetMapping("/getDetails/{id}")
    public Result<?> getDetails(@PathVariable Integer id) {
        Object details = medicineService.getDetails(id);
        return Result.success(details);
    }

    @GetMapping("/selectBySearch")
    public Result<?> selectBySearch(@RequestParam String Search) {
        List<Medicine> medicines = medicineService.selectBySearch(Search);
        return Result.success(medicines);
    }
}
