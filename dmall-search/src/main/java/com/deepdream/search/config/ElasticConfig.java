package com.deepdream.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch 配置
 * @author wangkai
 */
@Configuration
public class ElasticConfig {
    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("ubuntu-server",9200,"http")
        );
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}
