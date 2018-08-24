package com.imooc.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.user.entity.UserInfo;
import com.imooc.user.exception.UserInfoException;
import com.imooc.user.repository.UserInfoRepository;
import com.imooc.user.service.UserInfoService;
import com.imooc.user.userenum.UserInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoServiceImpl
 * @Description: 用户服务service实现类
 * @date 2018/7/30 15:05
 */
@Component
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * title: findByOpenid
     * version: v1.0
     * describe: 根据openid获取用户信息
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 15:02
     *
     * @param openid
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        try {
            log.info("service查询用户信息,接收的参数是:{}",openid);
            UserInfo userInfo = userInfoRepository.findByOpenid(openid);
            log.info("service查询用户信息得到的结果是:{}", JSON.toJSONString(userInfo));
            return userInfo;
        }catch (Exception e){
            log.error("根据openid查询用户信息异常!");
            throw new UserInfoException(UserInfoEnum.FIND_BY_OPENID_ERROR);
        }
    }
}
