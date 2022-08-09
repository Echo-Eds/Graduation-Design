package com.springboot.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.pojo.UserCollection;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/5 19:30
 */
public interface UserCollectionService extends IService<UserCollection> {
    List<UserCollection> getUserCollection(Integer userId);
    Page<UserCollection> budges(Integer pageNum, Integer pageSize);
    Object addCollection(UserCollection userCollection);
    Object deleteColl(Integer title_id);

}
