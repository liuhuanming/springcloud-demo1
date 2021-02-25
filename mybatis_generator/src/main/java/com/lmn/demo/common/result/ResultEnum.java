package com.lmn.demo.common.result;

/**
 * 规定: 这里是可以自己定义的，方便与前端交互即可
 */
public enum ResultEnum {
    // 默认错误
    ERROR("-1", "失败"),
    NOT_TIME("-3", "不在时间内"),
    NOT_FOUND_RESULT("-4", "未找到结果集"),

    SUCCESS("200", "成功"),
    NOT_FOUND("404", "资源未发现！"),

    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),

    /* 参数错误*/
    PARAM_NOT_VALID("101", "参数无效"),
    PARAM_IS_BLANK("102", "参数为空"),
    PARAM_TYPE_ERROR("103", "参数类型错误"),
    PARAM_NOT_COMPLETE("104", "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN("201", "用户未登录"),
    USER_ACCOUNT_EXPIRED("202", "账号已过期"),
    USER_CREDENTIALS_ERROR("203", "密码错误"),
    USER_CREDENTIALS_EXPIRED("204", "密码过期"),
    USER_ACCOUNT_DISABLE("205", "账号不可用"),
    USER_ACCOUNT_LOCKED("206", "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST("207", "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST("208", "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS("209", "账号下线"),

    /* 业务错误 */
    NO_PERMISSION("403", "没有权限");


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
