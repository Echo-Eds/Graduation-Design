package com.springboot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.MedicineMapper;
import com.springboot.demo.mapper.MedicineResourceMapper;
import com.springboot.demo.pojo.Medicine;
import com.springboot.demo.pojo.MedicineResource;
import com.springboot.demo.service.MedicalResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/15 16:34
 * 后台
 */
@RestController
@RequestMapping("/medicineResource")
public class MedicineResourceController {
    @Resource
    MedicalResourceService medicalResourceService;
    @PostMapping
    public Result<?> save(@RequestBody MedicineResource medicineResource) {
        medicalResourceService.saveRes(medicineResource);
        return Result.success();
    }

    @PutMapping("/edit")
    public Result<?> update(@RequestBody MedicineResource medicineResource) {
        medicalResourceService.updateRes(medicineResource);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {
        medicalResourceService.deleteByID(id);
        return Result.success();
    }
    @GetMapping
    //分页查询接受参数
//    当前页，总页面数，模糊查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        //hutool插件判断serach是否为空,nike_name为null时查询条件
        Page<MedicineResource> medicineResourcePage = medicalResourceService.findPage(pageNum, pageSize, search);
        return Result.success(medicineResourcePage);
    }

    /*
    以下为前台接口
     */
    /**
     * 获取所有信息返回前端
     * @return
     */
    @GetMapping("/getAll")
    public Result<?> getAll() {
        List<MedicineResource> all = medicalResourceService.getAll();
        return Result.success(all);
    }

    @GetMapping("/getType")
    public Result<List<MedicineResource>> getType() {
        List<MedicineResource> type = medicalResourceService.getType();
        return Result.success(type);
    }

    @GetMapping("/selectByType")
    public Result<?> selectByType(@RequestParam String Type) {
        List<MedicineResource> type = medicalResourceService.selectByType(Type);
        return Result.success(type);
    }

    @GetMapping("/selectBySearch")
    public Result<?> selectBySearch(@RequestParam String Search) {
        List<MedicineResource> search = medicalResourceService.selectBySearch(Search);
        return Result.success(search);
    }
}
