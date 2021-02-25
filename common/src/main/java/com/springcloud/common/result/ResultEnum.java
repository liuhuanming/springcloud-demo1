package com.springboot.common.result;

/**
 * 规定: 这里是可以自己定义的，方便与前端交互即可
 */
public enum ResultEnum {
    // 默认错误
    ERROR("-1", "失败"),
    NOT_TIME("-3", "不在时间内"),
    NOT_FOUND_RESULT("-4", "未找到结果集"),

    SUCCESS("200", "成功"),


    INTERNAL_SERVER_ERROR("5000", "服务器内部错误"),

    /* 参数错误*/
    PARAM_NOT_VALID("1001", "参数无效"),
    PARAM_IS_BLANK("1002", "参数为空"),
    PARAM_TYPE_ERROR("1003", "参数类型错误"),
    PARAM_NOT_COMPLETE("1004", "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN("2001", "用户未登录"),
    USER_ACCOUNT_EXPIRED("2002", "账号已过期"),
    USER_CREDENTIALS_ERROR("2003", "密码错误"),
    USER_CREDENTIALS_EXPIRED("2004", "密码过期"),
    USER_ACCOUNT_DISABLE("2005", "账号不可用"),
    USER_ACCOUNT_LOCKED("2006", "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST("2007", "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST("2008", "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS("2009", "账号下线"),
    USER_ACCOUNT_REG_ERROR("2100", "用户注册失败"),



    /* 业务错误 */
    NO_PERMISSION("4003", "没有权限"),
    NOT_FOUND("4004", "资源未发现！"),
    ;
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
