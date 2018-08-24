package com.imooc.product.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.product.dto.ProductDTO;
import com.imooc.product.result.Result;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderController
 * @Description: 商品服务用于给订单服务提供接口的controller
 * @date 2018/7/11 14:55
 */
@Controller
@RequestMapping(value = "order",method = {RequestMethod.POST,RequestMethod.GET})
@Slf4j
public class OrderController {

    @Autowired
    private ProductService productService;

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductList
     * @Description: 获取商品列表
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductList")
    @ResponseBody
    public Result getProductList(){
        log.info("无参数接口,controller开始获取商品列表......");
        Result result = productService.getProductList();
        log.info("controller获取商品列表的结果是:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductStockById")
    @ResponseBody
    public Result getProductStockById(HttpServletRequest request, ProductDTO productDTO,@RequestParam("productId") String productId){
        log.info("controller开始根据商品ID获取商品库存,接收的参数是:{}",JSON.toJSONString(productDTO));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ObjectUtils.isEmpty(productDTO.getProductId())){
            log.info("controller开始根据商品ID获取商品库存请求参数不全!");
            return ResultUtil.error();
        }
        Result result = productService.getProductStockById(productDTO);
        log.info("controller根据商品ID获取商品库存的结果是:{}",JSON.toJSONString(result));
        return result;
    }

}
