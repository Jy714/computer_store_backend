package com.Jy714.computerStore.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JSON格式的数据进行响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    // 状态码
    private Integer code;
    //描述信息
    private String msg;
    //数据类型
    private Object data;

    public static Result success(Integer code,String msg){
        return new Result(code,msg,null);
    }

    public static Result success(Integer code,Object data){
        return new Result(code,"sucess",data);
    }

    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
}
