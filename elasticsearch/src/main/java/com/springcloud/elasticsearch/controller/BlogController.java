package com.springcloud.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.springcloud.configuration.result.Response;
import com.springcloud.configuration.result.Result;
import com.springcloud.configuration.result.ResultEnum;
import com.springcloud.configuration.util.MathUtils;
import com.springcloud.elasticsearch.entity.User;
import com.springcloud.elasticsearch.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private EsService esService;

    @ApiOperation(value = "创建索引并关系映射")
    @PutMapping("/index/{indexName}")
    public Result<String> createIndex(@PathVariable("indexName") String indexName){
        Boolean exist = esService.existsIndex(indexName);
        if (exist) {
            return Response.error(ResultEnum.REPEAT_REGISTER, "索引名：" + indexName + "的索引已经创建，不用重复创建！");
        }
        CreateIndexResponse createIndexResponse = esService.createIndex(indexName);
        return Response.ok(createIndexResponse.toString());
    }
    @ApiOperation(value = "添加数据")
    @PutMapping("/blog")
    public Result<String> save(){
        User user = new User();
        user.setId(2);
        user.setUsername("我是刘二蛋222");
        user.setBirthday(new Date());
        user.setAddress("地址"+ Math.random()*10);
        user.setQq(Math.random()*100+"");
        IndexResponse indexResponse= esService.insertDocument("blog", "blog", JSON.toJSONString(user), user.getId()+"");
        return Response.ok(indexResponse.toString());
    }

    @ApiOperation(value = "根据id查询Es")
    @GetMapping("/blog/{id}")
    public Result<Map<String, Object>> getEsUserById(@PathVariable("id") String id){
        if (StringUtils.isEmpty(id)){
            return Response.error(ResultEnum.ERROR,"Id不能为空！");
        }
        // 先判断索引是否存在
        boolean exist = esService.existsIndex("blog");
        if (!exist) {
            return Response.error(ResultEnum.ERROR,"blog索引不存在");
        }
        GetResponse document = null;
        try {
            document = esService.getDocument("blog", "blog", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> sourceAsMap = document.getSourceAsMap();//检索文档(Map<String, Object>形式)
        return Response.ok(sourceAsMap);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/blog")
    public Result<String> update(User user, String _id){
        if (StringUtils.isEmpty(user.getId())) {
            return Response.error(ResultEnum.ERROR,"Id不能为空！");
        }
        user.setBirthday(new Date());
        String json = JSON.toJSONString(user);
        UpdateResponse response = null;
        response = esService.updateDocument("blog", "blog", json, _id);
        return Response.ok(response.toString());
    }

    @ApiOperation(value = "批量添加数据")
    @PutMapping("/bulk")
    public Result<String> bulkSave(){
        List<String> userJsonList = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 10000; i++) {
            user = new User();
            user.setId(100+i);
            user.setUsername("测试批量"+(100+i));
            user.setBirthday(new Date());
            user.setAddress("地址"+ Math.random()*10);
            user.setQq(MathUtils.makeUpNewData(Thread.currentThread().hashCode()+"", 3) + MathUtils.randomDigitNumber(9));
            userJsonList.add(JSON.toJSONString(user));
        }
        BulkResponse bulkResponse = esService.bulkInsert(userJsonList, "blog", "blog");
        return Response.ok(bulkResponse.toString());
    }
}

