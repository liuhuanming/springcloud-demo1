package com.lmn.mybatisplus.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/***
 * 返回json对象封装类（属性为null，不返回）
 * @author zhangda
 * version：2.0
 * 更新内容：加入泛型
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RM2<T> {
    public final static boolean SUCCESS = true;
    public final static boolean ERROR = false;

    public RM2() {
    }

    public RM2(boolean isSuccess) {
        this.success = isSuccess;
    }

    public RM2(boolean isSuccess, String msg) {
        this.success = isSuccess;
        this.msg = msg;
    }

    public RM2(boolean isSuccess, String msg, String errorCode) {
        this.success = isSuccess;
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public RM2(boolean isSuccess, List<T> list) {
        this.success = isSuccess;
        this.list = list;
    }

    public RM2(boolean isSuccess, Integer totalCount, List<T> list) {
        this.success = isSuccess;
        this.totalCount = totalCount;
        this.list = list;
    }

    public RM2(boolean isSuccess, Long recordsTotal, List<T> data) {
        this.success = isSuccess;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
        this.data = data;
    }

    public RM2(boolean isSuccess, T object) {
        this.success = isSuccess;
        this.object = object;
    }

    private boolean success;
    private String msg;
    private String errorCode;
    private List<T> list;
    private T object;
    private Integer totalCount;

    private List<T> data;
    private Long recordsTotal;

    private Long recordsFiltered;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
