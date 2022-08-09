package com.springboot.demo.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.Config.Result;
import com.springboot.demo.mapper.UserCollectionMapper;
import com.springboot.demo.pojo.UserCollection;
import com.springboot.demo.service.UserCollectionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/5 19:30
 */
@Service("UserCollectionServiceImpl")
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection>
        implements UserCollectionService {
    @Override
    public List<UserCollection> getUserCollection(Integer userId) {
        return baseMapper.getUserCollection(userId);
    }

    @Override
    public Page<UserCollection> budges(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<UserCollection> wrapper = Wrappers.lambdaQuery();
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public Object addCollection(UserCollection userCollection) {
        //        SFunction<UserCollection,Integer> function = UserCollection::getTitleId;
//        wrapper.eq(function,userCollection.getTitleId());
        //ObjectUtil.isNull判断当前是否为空 为空说明没有收藏
        //判断是否已经收藏过了
        LambdaQueryWrapper<UserCollection> wrapper = Wrappers.<UserCollection>lambdaQuery();

        wrapper.eq(UserCollection::getTitleId, userCollection.getTitleId()).eq(UserCollection::getUserId, userCollection.getUserId());
        UserCollection selectOne = baseMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(selectOne)) {
            return baseMapper.insert(userCollection);
        }
        return -1;
    }

    /**
     * 取消收藏
     *
     * @param title_id
     * @return
     */
    @Override
    public Object deleteColl(Integer title_id) {
        LambdaQueryWrapper<UserCollection> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserCollection::getTitleId, title_id);
        return baseMapper.delete(wrapper);
    }
}
