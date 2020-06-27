package com.springcloud.common.exception;

import cn.hutool.http.HttpStatus;
import com.springcloud.common.result.ResultEnum;
import lombok.Setter;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 13:31
 */
@Setter
public class MyException extends RuntimeException {
    private String status = ResultEnum.ERROR.toString();

    public MyException(String msg) {
        super(msg);
    }

    public MyException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.toString();
    }
}
