package com.imooc.user.userenum;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoEnum
 * @Description: 用户服务枚举类
 * @date 2018/7/30 15:09
 */
@Getter
public enum UserInfoEnum {

    FIND_BY_OPENID_ERROR(500,"根据openid获取用户信息异常!")
    ;

    private Integer code;

    private String message;

    UserInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
