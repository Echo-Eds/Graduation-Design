package com.springboot.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.*;
import com.springboot.demo.pojo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/5 19:52
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    ProfessorMapper professorMapper;
    @Resource
    MedicineMapper medicineMapper;
    @Resource
    MedicineIconPathMapper medicineIconPathMapper;
    @Resource
    MedicineResourceIconPathMapper medicineResourceIconPathMapper;
    @Resource
    ProfIconPathMapper profIconPathMapper;

    //
    @GetMapping("/proIconPath")
    public Result<?> GetProfessorIcon(@RequestParam(defaultValue = "") String iconPath) {
//        LambdaQueryWrapper<Professor> wrapper = Wrappers.lambdaQuery();
////        wrapper.select(Professor::getId, Professor::getIconPath);
////        List<Professor> professors = professorMapper.selectList(wrapper);
        LambdaQueryWrapper<ProfIconPath> wrapper = Wrappers.lambdaQuery();
        wrapper.select(ProfIconPath::getId, ProfIconPath::getProfessorid, ProfIconPath::getIconPath);
        List<ProfIconPath> profIconPaths = profIconPathMapper.selectList(wrapper);
        return Result.success(profIconPaths);
    }

    @GetMapping("/medIconPath")
    public Result<?> GetMedIcon(@RequestParam(defaultValue = "") String iconPath) {
        LambdaQueryWrapper<MedicineIconPath> wrapper = Wrappers.lambdaQuery();
        wrapper.select(MedicineIconPath::getId, MedicineIconPath::getIconPath, MedicineIconPath::getMedicineid);
        List<MedicineIconPath> medicineIconPaths = medicineIconPathMapper.selectList(wrapper);
        return Result.success(medicineIconPaths);
    }

    @GetMapping("/medResourceIconPath")
    public Result<?> GetMedResourceIcon(@RequestParam(defaultValue = "") String iconPath) {
        LambdaQueryWrapper<MedicineResourceIconParth> wrapper = Wrappers.lambdaQuery();
        wrapper.select(MedicineResourceIconParth::getId, MedicineResourceIconParth::getIconPath, MedicineResourceIconParth::getMedicineresourceid);
        List<MedicineResourceIconParth> medicineResources = medicineResourceIconPathMapper.selectList(wrapper);
        return Result.success(medicineResources);
    }

}
