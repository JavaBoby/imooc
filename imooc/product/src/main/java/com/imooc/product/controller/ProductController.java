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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductController
 * @Description: 商品服务controller
 * @date 2018/7/27 16:22
 */
@Controller
@RequestMapping(value = "product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * title: getProductList
     * version: v1.0
     * describe: 获取所有商品
     * author: 刘海洋
     * creat_date: 2018/7/27
     * creat_time: 16:22
     **/
    @RequestMapping(value = "getProductList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result getProductList(){
        Result result = productService.getProductList();
        log.info("controller获取所有商品得到的结果是:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数
     * @date 2018/7/11 14:55
     */
    @RequestMapping(value = "getProductStockById",method = RequestMethod.POST)
    @ResponseBody
    public Result getProductStockById(HttpServletRequest request, ProductDTO productDTO){
        log.info("controller开始根据商品ID获取商品库存,接收的参数是:{}",JSON.toJSONString(productDTO));
        if(ObjectUtils.isEmpty(productDTO.getProductId())){
            log.info("controller开始根据商品ID获取商品库存请求参数不全!");
            return ResultUtil.error();
        }
        Result result = productService.getProductStockById(productDTO);
        log.info("controller根据商品ID获取商品库存的结果是:{}",JSON.toJSONString(result));
        return result;
    }
}
