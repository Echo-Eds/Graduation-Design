package com.springboot.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.ProfessorMapper;
import com.springboot.demo.pojo.Professor;
import com.springboot.demo.pojo.User;
import com.springboot.demo.pojo.UserCollection;
import com.springboot.demo.pojo.UserQuestionReply;
import com.springboot.demo.service.ProfessorInfoService;
import com.springboot.demo.service.UserQuestionReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专家个人信息
 * @author LiZeyuan
 * @date 2022/4/24 15:11
 */

@RestController
@RequestMapping("/prfInfo")
@RequiredArgsConstructor
public class ProfessorInfoController {
    @Resource
    ProfessorInfoService professorInfoService;

    private final UserQuestionReplyService userQuestionReplyService;
    /**
     * 更新信息
     * @param professor
     * @return
     */
    @PutMapping("/updateProfInfo")
    public Result<?> updateProfInfo(@RequestBody Professor professor) {
        Object proInfo = professorInfoService.updateProInfo(professor);
        return Result.success(proInfo);
    }

    /**
     * 查询当前专家所有的回复信息，分页
     * @param professorId
     * @return
     */
    @GetMapping("/selectAllMyRep")
    public Result<?> selectAllMy(@RequestParam Integer professorId,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<UserQuestionReply> allMyRep = userQuestionReplyService.getAllMyRep(professorId,pageNum,pageSize);
        return Result.success(allMyRep);
    }
    /**
     * 删除当前回复
     * @return
     */
    @DeleteMapping("/deleteRep/{id}")
    public Result<?> deleteRep(@PathVariable Integer id){
        userQuestionReplyService.deleteMyRep(id);
        return Result.success();
    }
}
