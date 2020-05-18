package com.deepdream.dmall.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis 配置
 * @author wangkai
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.deepdream.dmall.product.dao")
public class MyBatisConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求超出最大页后调回首页
        paginationInterceptor.setOverflow(true);
        // 设置最大每页最大限制数 默认500条
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}
