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
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: HystrixController
 * @Description: 服务容错controller
 * @date 2018/7/11 16:15
 */
@Controller
@RequestMapping(value = "hystrix",method = {RequestMethod.POST,RequestMethod.GET})
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")//提供全局的降级服务
public class HystrixController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量(feign方式)
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductStockById",method = RequestMethod.POST)
    @ResponseBody
    public Result getProductStockById(HttpServletRequest request, @RequestParam("productId") String productId){
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
   // @HystrixCommand(fallbackMethod = "fallback")//提供服务降级
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })//提供服务降级,这里默认使用全局服务降级,并设置超时时间
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//设置熔断器
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//设置最小请求数
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//设置休眠窗口的时间,休眠窗口打开,熔断器处于OPEN状态,主逻辑关闭,由fallback返回错误,到达休眠窗时间,熔断器处于HALF OPEN状态,开始向主逻辑发送定量请求,请求成功比例达到条件则关闭断路器,恢复主逻辑,此时断路器处于CLOSE状态,如果请求成功比例没有达到条件,则关闭断路器,状态为CLOSE
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//设置请求触发熔错机制的比例
//    })
    @HystrixCommand
    @RequestMapping(value = "getProductStockByIdAndRestemp",method = RequestMethod.GET)
    @ResponseBody
    public Result getProductStockByIdAndRestemp(HttpServletRequest request, @RequestParam("number") Integer number){
        log.info("controller开始根据商品ID获取商品库存,接收的参数是:{}",JSON.toJSONString(number));
        if(number % 2 == 0){
            return ResultUtil.success();
        }
        //第一种方式
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.getForObject("http://localhost:9090/order/getProductList",Result.class);
//          throw new RuntimeException("发生异常啦!");
        //第二种方式
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
//        url = url + "/order/getProductList";
//        RestTemplate restTemplate = new RestTemplate();
//        Result result = restTemplate.getForObject(url,Result.class);
        //第三种方式
//        Result result = restTemplate.getForObject("http://PRODUCT/order/getProductList",Result.class);
        log.info("controller根据商品ID获取商品库存的结果是:{}",JSON.toJSONString(result));
        return result;
    }

    private Result fallback(HttpServletRequest request, ProductDTO productDTO){
        return ResultUtil.setResult(500,"太拥挤啦,请稍后重试!",null);
    }
    private Result defaultFallback(){
        return ResultUtil.setResult(500,"默认提示:太拥挤啦,请稍后重试!",null);
    }
}
