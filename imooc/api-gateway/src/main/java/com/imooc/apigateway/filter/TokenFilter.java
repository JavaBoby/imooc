package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: TokenFilter
 * @Description: 验证token过滤器(前置过滤器)
 * @date 2018/7/30 11:14
 */
@Component
public class TokenFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * title: run
     * version: v1.0
     * describe: 这里是zuul网关对我们业务进行验证的地方
     * author: 刘海洋
     * creat_date: 2018/7/30
     * creat_time: 11:20
     **/
    @Override
    public Object run() throws ZuulException {
        //通过zuul自己封装的方法获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取http请求
        HttpServletRequest request = requestContext.getRequest();
        //获取请求内容,这里时从URL里获取,也可以通过cookie,header里面获取
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            //设置请求不通过
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
