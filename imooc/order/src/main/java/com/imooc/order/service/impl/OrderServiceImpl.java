package com.imooc.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.dto.ProductDTO;
import com.imooc.order.exception.OrderException;
import com.imooc.order.orderenum.ResultEnum;
import com.imooc.order.result.Result;
import com.imooc.order.service.OrderService;
import com.imooc.order.util.KeyUtil;
import com.imooc.product.entity.ProductInfo;
import com.imooc.product.repository.ProductMapper;
import com.imooc.user.entity.UserInfo;
import com.imooc.user.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderServiceImpl
 * @Description: 订单服务service实现类
 * @date 2018/7/13 11:50
 */
@Service
@Component
@Slf4j
public class OrderServiceImpl implements OrderService {

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量
     * @date 2018/7/11 14:55
     */
    @Override
    public Result getProductList() {
        return null;
    }

    @Override
    public Result getProductStockById(String productId) {
        log.info("service开始根据ID获取某一商品的库存数量,接收的参数是:{}", JSON.toJSONString(productId));
        //1.restTemplate第一种使用方式
        RestTemplate restTemplate = new RestTemplate();
        Result object = restTemplate.getForObject("http://localhost:9090/order/getProductStockById", Result.class);
        log.info("订单服务service根据商品ID获取的商品库存数量是:{}",object);
        return object;
    }
}
