package com.springcloud.common.vo;

import com.springcloud.common.util.CopyUtil;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @date: 2021-02-28 8:36
 */
@Data
public class PageInfo<M> {
    private int page;//当前页码
    private int pageSize;//页面大小
    private String sidx;//排序字段
    private String sord;//排序方式

    private List<M> rows;//分页结果
    private int records;//总记录数
    private int total;//总页数

    /**
     * 获取统一分页结果
     */
    public static <M> PageInfo<M> of(Page page, Class<M> entityModelClass) {
        int records = (int) page.getTotalElements();
        int pageSize = page.getSize();
        int total = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;

        PageInfo<M> pageInfo = new PageInfo<>();
        pageInfo.setPage(page.getNumber() + 1);//页码
        pageInfo.setPageSize(pageSize);//页面大小
        pageInfo.setRows(CopyUtil.copyList(page.getContent(), entityModelClass));//分页结果
        pageInfo.setRecords(records);//总记录数
        pageInfo.setTotal(total);//总页数
        return pageInfo;
    }
}
