package com.springcloud.elasticsearch.controller;


import com.alibaba.fastjson.JSON;
import com.springcloud.configuration.result.Response;
import com.springcloud.configuration.result.Result;
import com.springcloud.elasticsearch.entity.BlogEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-26 21:45
 */
@Api(value = "ES",tags = "文章ES模块")
@RequestMapping("/es")
@RestController
public class BlogController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @ApiOperation(value = "创建索引并关系映射")
    @PutMapping("/index")
    public Result<String> createIndex(){
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = null ;
        try {
            createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg = "createIndex: " + JSON.toJSONString(createIndexResponse);
        return Response.ok(msg);
    }
    @ApiOperation(value = "添加数据")
    @PutMapping("/blog")
    public Result<String> save(){
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId("1");
        blogEntity.setTitle("测试");
        blogEntity.setIntroduce("我是一个介绍");
        blogEntity.setContent("四脚朝天四份，内容很长了");
        blogEntity.setAuthor("刘二蛋");

        IndexRequest indexRequest = new IndexRequest("blog", "doc", blogEntity.getId());
        indexRequest.source(JSON.toJSONString(blogEntity), XContentType.JSON);
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok(indexResponse.toString());
    }
    @ApiOperation(value = "判断索引是否存在")
    @GetMapping("/index/{indexName}")
    public Result<String> existsIndex(@PathVariable("indexName") String indexName){
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        boolean exists = false;
        try {
            exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok(exists?"是":"否");
    }
}

