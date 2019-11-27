package com.springcloud.elasticsearch.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;
import java.util.Map;

public interface EsService {
    GetResponse getDocument(String index, String type, String id) throws Exception;

    List<Map<String, Object>> searchBySearchSourceBuilde(String index, String type, String text, SearchSourceBuilder searchSourceBuilder);

    Boolean existsIndex(String indexName);

    IndexResponse insertDocument(String index, String type, String info, String id);

    UpdateResponse updateDocument(String index, String type, String text, String id);

    CreateIndexResponse createIndex(String indexName);

    boolean deleteIndex(String indexName) throws Exception;

    BulkResponse bulkInsert(List<String> jsonStrList, String index, String type);
}
