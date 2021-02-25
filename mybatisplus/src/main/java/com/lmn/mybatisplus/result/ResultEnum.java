package com.lmn.mybatisplus.result;


public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    ERROR("-1", "失败"),
    NOT_TIME("-3", "不在时间内"),
    REPEAT_REGISTER("-2", "该用户已注册"),
    NOT_FOUND_RESULT("-4", "未找到结果集"),

    SUCCESS("200", "成功"),
    NOT_FOUND("404", "不存在该用户"),

    INTERNAL_SERVER_ERROR("500", "服务器内部错误");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
