package com.springcloud.elasticsearch.service.impl;

import com.google.common.collect.Lists;
import com.springcloud.elasticsearch.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EsServiceImpl implements EsService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    /**
     * 分页条件查询
     * @param index,type,text,searchSourceBuilder
     * @param searchSourceBuilder
     */
    public List<Map<String, Object>> searchBySearchSourceBuilde(String index, String type, String text,
                                                     SearchSourceBuilder searchSourceBuilder){
        // 组装SearchRequest请求
        SearchRequest searchRequest = new SearchRequest(index);
        // 创建匹配查询构造器
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("username", text);
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        // 装配查询
        boolBuilder.must(matchQueryBuilder);
        searchSourceBuilder.query(boolBuilder);
        // 分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);

        // 降序
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("id");
        fieldSortBuilder.order(SortOrder.DESC);
        searchSourceBuilder.sort(fieldSortBuilder);

        // 构造高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>"); // 前缀
        highlightBuilder.postTags("</span>"); //尾缀
        // 高亮字段
        highlightBuilder.field("username").field("address");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);

        // 同步获取SearchResponse结果
        SearchResponse searchResponse = null;
        // 存放结果集
        List<Map<String, Object>> result = Lists.newLinkedList();
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            if (searchHits !=null && searchHits.length>0) {
                for (SearchHit searchHit : searchHits) {
                    Map<String, Object> map = searchHit.getSourceAsMap();
                    // 替换高亮
                    Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                    HighlightField highlightField = highlightFieldMap.get("username");
                    String highUserName = highlightField.fragments()[0].toString();
                    map.put("username", highUserName);
                    result.add(map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 是否存在index
     * @param indexName
     * @return
     */
    public Boolean existsIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        boolean exists = false;
        try {
            exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  exists;
    }

    /**
     * 根据ID查询记录
     *
     * @param
     * @return
     */
    public GetResponse getDocument(String index, String type, String id) throws Exception{
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("索引：{},类型：{},未打到id为{}的记录", index, type, id);
            if (e.status() == RestStatus.NOT_FOUND) {
                log.error("未找到!");
            }
        }
        return getResponse;
    }

    /**
     * 添加记录
     * @param
     * @return
     */
    public IndexResponse insertDocument(String index, String type, String info, String id) {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        indexRequest.source(info, XContentType.JSON);
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  indexResponse;
    }

    @Override
    public UpdateResponse updateDocument(String index, String type, String text, String id) {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        updateRequest.doc(text,XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updateResponse;
    }

    /**
     * 创建索引
     * @param indexName
     * @return
     */
    public CreateIndexResponse createIndex(String indexName) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse createIndexResponse = null ;
        try {
            createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("es服务创建索引：{}，返回结果信息：{}", indexName, createIndexResponse);
        return createIndexResponse;
    }

    /**
     * 删除索引
     * @param indexName
     * @return
     */
    public boolean deleteIndex(String indexName) throws Exception{
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse deleteResponse = null ;
        try {
            deleteResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("es删除{}索引失败",indexName);
            if (e.status() == RestStatus.NOT_FOUND) {
                return true;
            } else {
                return false;
            }
        }
        log.info("es删除索引{},响应结果：{}", indexName, deleteResponse);
        return deleteResponse.isAcknowledged();
    }

    /**
     * 批量插入  批量修改删除原理相同，也可混合 只需在 bulkRequest.add 不同请求即可
     * 也可以插入时设置id
     * @param jsonStrList  待插入集合 注：每个String元素需为json字符串
     * @param index 索引
     * @return
     */
    public BulkResponse bulkInsert(List<String> jsonStrList, String index, String type) {
        BulkRequest bulkRequest = new BulkRequest();
        int i = 0;
        for (String jsonStr : jsonStrList) {
            IndexRequest indexRequest = new IndexRequest(index, type);
            indexRequest.source(jsonStr, XContentType.JSON);
            bulkRequest.add(indexRequest); // 加入到批量请求bulk
        }

        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bulkResponse;
    }
}
