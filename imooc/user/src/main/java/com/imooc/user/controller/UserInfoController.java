package com.imooc.user.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.entity.UserInfo;
import com.imooc.user.result.Result;
import com.imooc.user.service.UserInfoService;
import com.imooc.user.userenum.UserLoginEnum;
import com.imooc.user.util.CookieUtil;
import com.imooc.user.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfoController
 * @Description: 用户服务controller
 * @date 2018/7/30 16:26
 */
@RestController
@RequestMapping(value = "user",method = {RequestMethod.POST,RequestMethod.GET})
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * title: login
     * version: v1.0
     * describe: 用户登录接口
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 16:28
     **/
    @RequestMapping(value = "login")
    @ResponseBody
    @CrossOrigin(allowCredentials = "true")//设置类或方法可以支持跨域请求,allowCredentials = "true"表示可以支持跨越请求
    public Result login(@RequestParam("openid") String openid, HttpServletResponse response,HttpServletRequest request){
        log.info("controller用户开始登录,接收的参数是:{}", JSON.toJSONString(openid));
        if(StringUtils.isEmpty(openid)){
            log.info("controller用户登录请求参数不全!");
            return ResultUtil.error();
        }
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if(ObjectUtils.isEmpty(userInfo)){
            log.info("没有此用户!");
            return ResultUtil.error();
        }
        if((UserLoginEnum.BUYER_CODE.getRole() != userInfo.getRole()) && (UserLoginEnum.SELLER_CODE.getRole() != userInfo.getRole())){
            log.info("用户角色错误!");
            return ResultUtil.setResult(500,"用户角色错误!",null);
        }
        if(userInfo.getRole().equals(UserLoginEnum.BUYER_CODE.getRole())){
            //设置cookie
            CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);
        }else{
            //判断是否登录
            Cookie cookie = CookieUtil.get(request, RedisConstant.TOKEN);
            if(!ObjectUtils.isEmpty(cookie) && !ObjectUtils.isEmpty(stringRedisTemplate.opsForValue().get(cookie.getValue()))){
                return ResultUtil.success();
            }else{
                String uuid = UUID.randomUUID().toString();
                //将openid存入redis
                stringRedisTemplate.opsForValue().set(uuid,openid);
                //设置cookie
                CookieUtil.set(response, RedisConstant.TOKEN,uuid,RedisConstant.maxAge);
            }
        }
        return ResultUtil.success();
    }
}
