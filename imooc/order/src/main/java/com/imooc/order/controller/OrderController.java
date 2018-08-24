package com.imooc.order.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.order.converter.OrderForm2OrderDTOConverter;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.dto.ProductDTO;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import com.imooc.order.orderenum.ResultEnum;
import com.imooc.order.result.Result;
import com.imooc.order.service.OrderService;
import com.imooc.order.util.ResultUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderController
 * @Description: 订单controller
 * @date 2018/7/11 16:15
 */
@Controller
@RequestMapping(value = "order",method = {RequestMethod.POST,RequestMethod.GET})
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductList
     * @Description: 获取商品列表
     * @date 2018/7/11 16:15
     */
    @RequestMapping(value = "getProductList")
    @ResponseBody
    public Result getProductList(){
        log.info("无参数接口,controller开始获取商品列表......");
        Result result = orderService.getProductList();
        log.info("controller获取商品列表的结果是:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量(feign方式)
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductStockById",method = RequestMethod.POST)
    @ResponseBody
    public Result getProductStockById(HttpServletRequest request, String productId){
        log.info("controller开始根据商品ID获取商品库存,接收的参数是:{}",JSON.toJSONString(productId));
        if(ObjectUtils.isEmpty(productId)){
            log.info("controller开始根据商品ID获取商品库存请求参数不全!");
            return ResultUtil.error();
        }
        Result result = orderService.getProductStockById(productId);
        log.info("controller根据商品ID获取商品库存的结果是:{}",JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockByIdAndRestemp
     * @Description: 根据ID获取某一商品的库存数量(RestTemplate方式)
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductStockByIdAndRestemp",method = RequestMethod.GET)
    @ResponseBody
    public Result getProductStockByIdAndRestemp(HttpServletRequest request, ProductDTO productDTO){
        log.info("controller开始根据商品ID获取商品库存,接收的参数是:{}",JSON.toJSONString(productDTO));
        if(ObjectUtils.isEmpty(productDTO.getProductId())){
            log.info("controller开始根据商品ID获取商品库存请求参数不全!");
            return ResultUtil.error();
        }
        //第一种方式
//        RestTemplate restTemplate = new RestTemplate();
//        Result result = restTemplate.getForObject("http://localhost:9090/order/getProductList",Result.class);
        //第二种方式
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
//        url = url + "/order/getProductList";
//        RestTemplate restTemplate = new RestTemplate();
//        Result result = restTemplate.getForObject(url,Result.class);
        //第三种方式
        Result result = restTemplate.getForObject("http://PRODUCT/order/getProductList",Result.class);
        log.info("controller根据商品ID获取商品库存的结果是:{}",JSON.toJSONString(result));
        return result;
    }
}
