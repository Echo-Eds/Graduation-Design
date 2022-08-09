package com.springboot.demo.controller;

import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.SkillsTraining;
import com.springboot.demo.service.SkillsTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 技能培训
 *
 * @author LiZeyuan
 * @date 2022/5/12 16:51
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/skillsTraining")
public class SkillsTrainingController {

    //Bean
    private final SkillsTrainingService skillsTrainingService;

    /**
     * 获取当前类型的培训类型
     * @return
     */
    @GetMapping("/getType")
    public Result<?> getSkillsType() {
        List<SkillsTraining> skillsTrainingServiceType = skillsTrainingService.getType();
        return Result.success(skillsTrainingServiceType);
    }

    @GetMapping("/getAll")
    public Result<?> getAll() {
        List<SkillsTraining> all = skillsTrainingService.getAll();
        return Result.success(all);
    }

    /**
     * 根据类型查询
     * @param Type
     * @return
     */
    @GetMapping("selectByType")
    public Result<?> selectByType(@RequestParam String Type) {
        List<SkillsTraining> selectByType = skillsTrainingService.selectByType(Type);
        return Result.success(selectByType);
    }

    @GetMapping("selectBySearch")
    public Result<?> selectBySearch(@RequestParam String Search) {
        List<SkillsTraining> selectSearch = skillsTrainingService.selectSearch(Search);
        return Result.success(selectSearch);
    }

    /**
     * 获取详细信息
     * @param id
     * @return
     */
    @GetMapping("/getDetails")
    public Result<?> getDetails(@RequestParam Integer id) {
        Object details = skillsTrainingService.getDetails(id);
        return Result.success(details);
    }
}
