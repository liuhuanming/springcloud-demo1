package com.lmn.demo.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * 请求返回类
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    //返回数据
    private T data;
    //返回码
    private String code;
    //返回sucess
    private Boolean sucess;
    // 返回描述
    private String msg;

    public Result<T> setCode(ResultEnum ResultEnum) {
        this.code = ResultEnum.getCode();
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSucess() {
        return sucess;
    }

    public Result<T> setSucess(Boolean sucess) {
        this.sucess = sucess;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

}
