package com.springboot.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.pojo.ProfessorDetails;
import com.springboot.demo.service.Impl.ProfessorDetailsServiceImpl;
import com.springboot.demo.service.ProfessorDetailsService;
import com.springboot.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 由于数据库的原因，这个controller用了两个service
 * 系统医师模块
 * @author LiZeyuan
 * @date 2022/4/24 17:28
 */
@RestController
@RequestMapping(value = "/professorDetails")
public class ProfessorDetailsController {
    @Resource
    ProfessorDetailsService professorDetailsService;

    @Resource
    ProfessorService professorService;
    /**
     * 下面两个分别获取相对应的专家类型和专家科室（主任医师，骨科等）
     * @return ProfessorType & & Department
     */
    @GetMapping("/getProType")
    public Result<?> getProType() {
        List<ProfessorDetails> professorType = professorDetailsService.getProfessorType();
        return Result.success(professorType);
    }

    @GetMapping("/getProDepart")
    public Result<?> getProDepart() {

        List<ProfessorDetails> professorDepartment = professorDetailsService.getProfessorDepartment();
        return Result.success(professorDepartment);
    }

    /**
     * 获取所有的专家信息 返回到前端默认展示界面
     * @return Professor
     */
    @GetMapping("/getAll")
    public Result<?> getAllProf() {
        List<Professor> allProfessor = professorService.getAll();
        return Result.success(allProfessor);
    }

    /**
     * 根据前端选择的type查找对应的prof
     * @param professorType
     * @return Professor
     */
    @GetMapping("/getProfByType")
    public Result<?> getProfByType(@RequestParam(defaultValue = "") String professorType) {
        List<Professor> listProfessorType = professorService.listProfessorType(professorType);
        return Result.success(listProfessorType);
    }

    /**
     *根据科室类型查找
     * @param professorDepartment
     * @return Professor
     */
    @GetMapping("/getProByDepartment")
    public Result<?> getProByDepartment(@RequestParam(defaultValue = "") String professorDepartment) {
        List<Professor> listProfessorDepartment = professorService.listProfessorDepartment(professorDepartment);
        return Result.success(listProfessorDepartment);
    }

    /**
     * 根据搜索关键词查找
     * @param search
     * @return
     */
    @GetMapping("/getProfessorBySearch")
    public Result<?> getProfessorBySearch(@RequestParam(defaultValue = "Li") String search) {
        List<Professor> searchProfessor = professorService.searchProfessor(search);
        return Result.success(searchProfessor);
    }

    /**
     * 查看医师详情controller 此处查基本信息，下一个查专家介绍
     * @param id
     * @return
     */
    @GetMapping("/selectOne/{id}")
    public Result<?> getOne(@PathVariable Integer id) {
        Object selectOne = professorService.selectOne(id);
        return Result.success(selectOne);
    }

    @GetMapping("/selectDetails")
    public Result<?> getDetails(@RequestParam Integer id) {
        Object professorDetails = professorDetailsService.getProfessorDetails(id);
        return Result.success(professorDetails);
    }
}
