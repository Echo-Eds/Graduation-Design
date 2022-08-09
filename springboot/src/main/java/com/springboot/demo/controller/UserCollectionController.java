package com.springboot.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Config.Result;
import com.springboot.demo.pojo.UserCollection;
import com.springboot.demo.service.UserCollectionService;
import lombok.RequiredArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏管理
 * @author LiZeyuan
 * @date 2022/5/5 19:51
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/myCollection")
public class UserCollectionController {
    private final UserCollectionService userCollectionService;

    @GetMapping("/getMyCollection")
    public Result<?> getMyCollection(@RequestParam Integer userId) {
        List<UserCollection> userCollectionList = userCollectionService.getUserCollection(userId);
        return Result.success(userCollectionList);
    }

    /**
     * 分页
     */
    @GetMapping("/budges")
    public Result<?> budges(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<UserCollection> budges = userCollectionService.budges(pageNum, pageSize);
        return Result.success(budges);
    }

    @PostMapping("/addToMyCollection")
    public Result<?> add(@RequestBody UserCollection userCollection) {
        Object object = userCollectionService.addCollection(userCollection);
//        判断service层传递的值多少，如果是1说明该用户已经收藏过了，弹出错误
        if(object.toString().equals("-1")){
            return Result.error("-1","您已经收藏过了");
        }
        return Result.success(object);
    }

    @DeleteMapping("/delete/{title_id}")
    public Result<?> deleteColl(@PathVariable Integer title_id) {
        userCollectionService.deleteColl(title_id);
        return Result.success();
    }
}
