package com.springcloud.common.result;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
 
/**
 * 请求返回类
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T>{
 
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回success
    private Boolean success;
    // 返回描述
    private String msg;

    public Result() {

    }

    public Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

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
     
     public Boolean getSuccess() {
        return success;
     }
   
     public Result<T> setSuccess(Boolean success) {
        this.success = success;
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
