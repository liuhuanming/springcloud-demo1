package com.lmn.mybatisplus.result;

public class Response {
    private final static Boolean SUCCESS = true;
    private final static Boolean ERROR = false;

    public static <T> Result<T> ok() {
        return new Result<T>().setCode(ResultEnum.SUCCESS).setSuccess(SUCCESS);
    }

    public static <T> Result<T> ok(ResultEnum Enum) {
        return new Result<T>().setCode(Enum).setSuccess(SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>().setCode(ResultEnum.SUCCESS).setSuccess(SUCCESS).setData(data);
    }

    public static <T> Result<T> error() {
        return new Result<T>().setCode(ResultEnum.ERROR).setSuccess(ERROR);
    }


    public static <T> Result<T> error(ResultEnum Enum, String msg) {
        return new Result<T>().setCode(Enum).setMsg((Enum.getMsg() + ":" + msg)).setSuccess(ERROR);
    }

    public static <T> Result<T> error(ResultEnum Enum) {
        return new Result<T>().setCode(Enum).setMsg(Enum.getMsg()).setSuccess(ERROR).setData(null);
    }

    public static <T> Result<T> response(String code) {
        return new Result<T>().setCode(code).setSuccess(SUCCESS);
    }

    public static <T> Result<T> response(String code, Boolean sucess, T data) {
        return new Result<T>().setCode(code).setSuccess(sucess).setData(data);
    }

}
