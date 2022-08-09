package com.springboot.demo.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiZeyuan
 * @date 2022/1/26 12:30
 * Mybatis 分页插件
 */

@Configuration
@MapperScan("com.springboot.demo.mapper")
public class MybaitsPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
//    @Bean 旧版
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        return new PaginationInnerInterceptor();
//    }
}
