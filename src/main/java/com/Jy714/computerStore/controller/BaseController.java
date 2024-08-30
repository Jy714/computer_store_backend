package com.Jy714.computerStore.controller;

import com.Jy714.computerStore.controller.ex.*;
import com.Jy714.computerStore.service.ex.*;
import com.Jy714.computerStore.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 控制层类的基类 */
@RestControllerAdvice
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    // 请求处理方法, 这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当项目中产生了异常 会被统一拦截到此方法中 这个方法此时就冲当时一个请求处理方法 方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class}) //用于统一处理抛出的异常
    public Result handleException(Throwable e){
        Result result = new Result();
        if(e instanceof UsernameDuplicatedException){
            result.setCode(4000);
            result.setMsg("用户名已被占用");
        }else if(e instanceof InsertException){
            result.setCode(4001);
            result.setMsg("注册时产生未知的异常");
        }else if(e instanceof UserNotFoundException){
            result.setCode(4002);
            result.setMsg("用户数据不存在的异常");
        }else if(e instanceof AddressCountLimitException){
            result.setCode(4003);
            result.setMsg("用户的收货地址超出上限的异常");
        }else if(e instanceof AddressNotFoundException){
            result.setCode(4004);
            result.setMsg("用户的收货地址数据不存在异常");
        }else if(e instanceof AccessDeniedException){
            result.setCode(4005);
            result.setMsg("非法访问的异常");
        }else if(e instanceof ProductNotFoundException){
            result.setCode(4006);
            result.setMsg("商品数据不存在的异常");
        }else if(e instanceof CartNotFoundException){
            result.setCode(4007);
            result.setMsg("购物车数据不存在的异常");
        }else if(e instanceof DeleteException){
            result.setCode(5001);
            result.setMsg("删除数据产生未知的异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setCode(5002);
            result.setMsg("用户的密码错误的异常");
        }else if(e instanceof FileEmptyException){
            result.setCode(6000);
        }else if(e instanceof FileSizeException){
            result.setCode(6001);
        }else if(e instanceof FileStatusException){
            result.setCode(6002);
        }else if(e instanceof FileUploadIOException){
            result.setCode(6003);
        }
        return result;
    }
}
