package com.springcloud.common.enums;

/**
 *  结果集Enum
 */
public enum ResultEnum {
	//这里是可以自己定义的，方便与前端交互即可
    ERROR("-1","操作失败"),
    NOT_TIME("-3","不在时间内"),
    REPEAT_REGISTER("-2", "该用户已注册"),
    NOT_FOUND_RESULT("-4","未找到结果集"),

    SUCCESS("200","操作成功"),
    NOT_FOUND("404","不存在该用户"),

//    LOGIN_TOKEN_EMPTY("3002","token为空"),
//    LOGIN_TOKEN_EXPIRE("3000","token过期，请重新登录"),
    LOGIN_TOKEN_EMPTY("3002","登陆超时，请重新登陆"),
    LOGIN_TOKEN_EXPIRE("3000","登陆超时，请重新登陆"),

    PASSWORD_CONFIRM_PASSWORD_INCONSISTENT("1998","新密码和确认密码不一致"),
    OLD_PASSWORD_INCORRECT("1999","原密码错误"),
    USERNAME_NONEXISTENT("2000","用户不存在"),
    PASSWORD_INCORRECT("2001","密码错误"),
    VERIFICATION_CODE_ERROR("2002", "手机验证码错误"),
    VERIFICATION_CODE_EXPIER("2003", "手机验证码错误或已过期"),
    PHONE_EMPTY("2004", "手机号不存在"),
    USER_EXPIER("2005", "账号已过期"),
    USER_NOSTARTUSE("2006", "账号尚未启用"),
    NO_PATIENT("2007","此患者暂无数据，处理中！"),

    USER_ROLE_EMPTY("4000","请选择用户或角色"),
    DEPT_EMPTY("4001","请选择科室，科室不能为空"),
    ROLE_EMPTY("4002","请选择角色，角色不能为空"),
    DIC_ITEM_CANNOT_EMPTY("4003","创建字典表字段不能为空"),
    DIC_AREADY_EXIST("4004","字典表已经存在"),
    COLUMN_HAS_NULL("4005","字段值存在空值不能默认不为空"),
    EXCEL_FIRST_NULL("4006","excel头不能为空"),
    FILE_IS_NULL("4007","文件不能为空"),
    EXCEL_TYPE_ERROR("4008","不是EXCEL文件"),
    IMAGE_TYPE_ERROR("4009","不是图片类型文件"),
    DELETE_ROLE_USEING("4010","此角色存在用户使用，不允许删除"),
    REPEAT_RECORD("4011","该记录存在重复字段表单编码"),
    CHECK_ORG("4012","此机构有在用用户，不可删除。"),
    CIFANGAN_YISHIYONG("4013","此方案已使用，不可删除"),

    INTERNAL_SERVER_ERROR("500","服务器内部错误");


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
