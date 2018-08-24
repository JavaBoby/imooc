package com.imooc.user.userenum;

import lombok.Getter;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserLoginEnum
 * @Description: 用户登录角色判断枚举类
 * @date 2018/7/30 16:55
 */
@Getter
public enum  UserLoginEnum {
    BUYER_CODE(1),
    SELLER_CODE(2)
    ;

    private Integer role;

     UserLoginEnum(Integer role) {
        this.role = role;
    }
}
