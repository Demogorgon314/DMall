package com.deepdream.search;

import com.alibaba.fastjson.JSON;
import com.deepdream.search.config.ElasticConfig;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
class DmallSearchApplicationTests {

    @Qualifier("restHighLevelClient")
    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }

    @Data
    static class User{
        private String userName;
        private String gender;
    }
    @Data
    @ToString
    static public class Account {
        private int account_number;

        private int balance;

        private String firstname;

        private String lastname;

        private int age;

        private String gender;

        private String address;

        private String employer;

        private String email;

        private String city;

        private String state;
    }
    @Test
    public void testIndex() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
//        indexRequest.id("1");
//        indexRequest.source("userName","zhangsan","age",18,"gender","男");
        User user = new User();
        user.setGender("男");
        user.setUserName("zhangsan1");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, ElasticConfig.COMMON_OPTIONS);
        log.info(index.toString());
    }

    @Test
    public void searchData() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        // 指定DSL
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(AggregationBuilders
                .terms("ageAgg")
                .field("age")
                .size(20)
                .subAggregation(AggregationBuilders
                        .terms("balanceAvg")
                        .field("avg")
                ));
        searchSourceBuilder.aggregation(AggregationBuilders.avg("ageBalanceAvg").field("balance"));
        log.info(searchSourceBuilder.toString());
        SearchRequest source = searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(source, ElasticConfig.COMMON_OPTIONS);
        log.info(search.toString());
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            String json = documentFields.getSourceAsString();
            log.info(json);
            Account account = JSON.parseObject(json, Account.class);
            log.info(account.toString());
        }

        Aggregations aggregations = search.getAggregations();
        Terms ageAgg = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            log.info("年龄：{}==>{}",keyAsString,bucket.getDocCount());

        }

        Avg ageBalanceAvg = aggregations.get("ageBalanceAvg");
        double value = ageBalanceAvg.getValue();
        log.info("ageBalanceAvg:{}",value);

    }



}
