package com.springboot.demo.controller;

import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.UserQuestionReply;
import com.springboot.demo.service.UserQuestionReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/13 21:12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/consultReply")
public class UserQuestionReplyController {
    private final UserQuestionReplyService userQuestionReplyService;

    @GetMapping("/getRep")
    public Result<?> getRep(@RequestParam Integer questionID) {
        List<UserQuestionReply> rep = userQuestionReplyService.getRep(questionID);
        return Result.success(rep);
    }

    @PostMapping("/addRep")
    public Result<?> addRep(@RequestBody UserQuestionReply userQuestionReply) {
        userQuestionReplyService.addRep(userQuestionReply);
        return Result.success();
    }
}
