package com.imooc.product.util;

import com.imooc.product.result.Result;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ResultUtil
 * @Description: 返回结果集工具类
 * @date 2018/7/11 15:30
 */
public class ResultUtil {

    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("操作成功!");
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(500);
        result.setMessage("操作失败!");
        return result;
    }

    public static Result setResult(Integer code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
