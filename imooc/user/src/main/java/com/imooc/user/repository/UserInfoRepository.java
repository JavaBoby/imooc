package com.imooc.user.repository;

import com.imooc.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoRepository
 * @Description: 用户服务repository
 * @date 2018/7/30 15:01
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

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
