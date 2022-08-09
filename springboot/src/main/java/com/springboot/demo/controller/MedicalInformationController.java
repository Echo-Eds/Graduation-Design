package com.springboot.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.MedicalInformation;
import com.springboot.demo.service.MedicalInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/12 21:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/medicalInformation")
public class MedicalInformationController {
    //Bean
    private final MedicalInformationService medicalInformationService;

    /**
     * 获取医药资讯列表
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("/getList")
    public Result<?> getList(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(defaultValue = "") String search) {
        Page<MedicalInformation> list = medicalInformationService.getList(pageNum, pageSize, search);
        return Result.success(list);
    }

    /**
     * 获取当前的专家用户的回复
     * @param id
     * @return
     */
    @GetMapping("/content/{id}")
    public Result<?> getContent(@PathVariable Integer id) {
        Object content = medicalInformationService.getContent(id);
        return Result.success(content);
    }

    @PostMapping("/insertContent")
    public Result<?> insertContent(@RequestBody MedicalInformation medicalInformation) {
        medicalInformationService.insertContent(medicalInformation);
        return Result.success();
    }
    
}
