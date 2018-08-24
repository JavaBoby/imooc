package com.imooc.user.service;

import com.imooc.user.entity.UserInfo;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoService
 * @Description: 用户服务service接口
 * @date 2018/7/30 15:03
 */
public interface UserInfoService {

    /**
     * title: findByOpenid
     * version: v1.0
     * describe: 根据openid获取用户信息
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 15:02
     **/
    UserInfo findByOpenid(String openid);
}
