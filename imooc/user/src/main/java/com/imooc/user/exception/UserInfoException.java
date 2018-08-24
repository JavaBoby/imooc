package com.imooc.user.exception;

import com.imooc.user.userenum.UserInfoEnum;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoException
 * @Description: 用户服务异常类
 * @date 2018/7/30 15:07
 */
public class UserInfoException extends RuntimeException{

    private Integer code;

    private String message;

    public UserInfoException(UserInfoEnum userInfoEnum){
        super(userInfoEnum.getMessage());
        this.code = userInfoEnum.getCode();
    }
}
