package com.springboot.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.UserQuestion;
import com.springboot.demo.service.UserQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/4 20:26
 */
@RestController
@RequestMapping("/consult")
//此注解可以省略@Autowired下面的构造器
//@RequiredArgsConstructor
public class UserQuestionController {

    //条件构造器 注入Bean 效果等同于@Resource
    private final UserQuestionService userQuestionService;

    @Autowired
    public UserQuestionController(UserQuestionService userQuestionService) {
        this.userQuestionService = userQuestionService;
    }

    /**
     * 计算需要加载的列表 分页
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("/getList")
    public Result<?> getQuestionList(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "") String search) {
        Page<UserQuestion> questionList = userQuestionService.getQuestionList(pageNum, pageSize, search);
        return Result.success(questionList);
    }

    /*
    下面两个分别获取内容和作者名
     */
    @GetMapping("/content")
    public Result<?> getContent(@RequestParam Integer titleId) {
        Object currentContent = userQuestionService.getContent(titleId);
        return Result.success(currentContent);
    }

    @GetMapping("/getAuthorName")
    public Result<?> getAuthorName(@RequestParam Integer userId) {
        String authorName = userQuestionService.getAuthorName(userId);
        return Result.success(authorName);
    }

    @PostMapping("/addContent")
    public Result<?> addContent(@RequestBody UserQuestion userQuestion) {
        userQuestionService.addContent(userQuestion);
        return Result.success();
    }
}
