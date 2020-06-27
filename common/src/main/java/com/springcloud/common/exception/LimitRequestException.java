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
public class LimitRequestException extends RuntimeException {
    private String status = ResultEnum.LIMIT_REQUESAT_ERROR.toString();

    public LimitRequestException(String msg) {
        super(msg);
    }

    public LimitRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.toString();
    }
}
