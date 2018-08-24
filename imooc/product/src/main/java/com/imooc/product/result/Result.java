package com.imooc.product.result;

import lombok.Data;
import lombok.ToString;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: Result
 * @Description: 返回的结果集类
 * @date 2018/7/11 15:28
 */
@Data
@ToString
public class Result {

    private Integer code;

    private String message;

    private Object data;
}
