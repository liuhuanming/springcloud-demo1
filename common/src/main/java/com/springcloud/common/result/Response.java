package com.springboot.common.result;

public class Response {
    private final static Boolean SUCCESS = true;
    private final static Boolean ERROR = false;

    public static <T> com.springboot.common.result.Result<T> ok() {
        return new com.springboot.common.result.Result<T>().setCode(com.springboot.common.result.ResultEnum.SUCCESS).setSuccess(SUCCESS);
    }

    public static <T> com.springboot.common.result.Result<T> ok(com.springboot.common.result.ResultEnum Enum) {
        return new com.springboot.common.result.Result<T>().setCode(Enum).setSuccess(SUCCESS);
    }

    public static <T> com.springboot.common.result.Result<T> ok(T data) {
        return new com.springboot.common.result.Result<T>().setCode(com.springboot.common.result.ResultEnum.SUCCESS).setSuccess(SUCCESS).setData(data);
    }

    public static <T> com.springboot.common.result.Result<T> error() {
        return new com.springboot.common.result.Result<T>().setCode(com.springboot.common.result.ResultEnum.ERROR).setSuccess(ERROR);
    }


    public static <T> com.springboot.common.result.Result<T> error(com.springboot.common.result.ResultEnum Enum, String msg) {
        return new com.springboot.common.result.Result<T>().setCode(Enum).setMsg((Enum.getMsg() + ":" + msg)).setSuccess(ERROR);
    }

    public static <T> com.springboot.common.result.Result<T> error(com.springboot.common.result.ResultEnum Enum) {
        return new com.springboot.common.result.Result<T>().setCode(Enum).setMsg(Enum.getMsg()).setSuccess(ERROR).setData(null);
    }

    public static <T> com.springboot.common.result.Result<T> response(String code) {
        return new com.springboot.common.result.Result<T>().setCode(code).setSuccess(SUCCESS);
    }

    public static <T> com.springboot.common.result.Result<T> response(String code, Boolean success, T data) {
        return new com.springboot.common.result.Result<T>().setCode(code).setSuccess(success).setData(data);
    }

}
