package com.imooc.user.util;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: CookieUtil
 * @Description: 设置cookie工具类
 * @date 2018/7/30 17:02
 */
public class CookieUtil {

    /**
     * title: set
     * version: v1.0
     * describe: 设置cookie
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 17:02
     **/
    public static void set(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * title: get
     * version: v1.0
     * describe: 获取cookie
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 18:50
     **/
    public static Cookie get(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if(!ObjectUtils.isEmpty(cookies)){
            for(Cookie cookie:cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
