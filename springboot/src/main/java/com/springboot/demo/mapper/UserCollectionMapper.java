package com.springboot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.pojo.UserCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/5/5 19:29
 */
public interface UserCollectionMapper extends BaseMapper<UserCollection> {
    List<UserCollection> getUserCollection(@Param("userId") Integer userId);
}
