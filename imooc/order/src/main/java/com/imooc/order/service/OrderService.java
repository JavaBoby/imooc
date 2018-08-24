package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;
import com.imooc.order.dto.ProductDTO;
import com.imooc.order.result.Result;
import com.imooc.order.util.ResultUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderService
 * @Description: 订单service接口
 * @date 2018/7/11 16:17
 */
@FeignClient(value = "product",fallback = OrderService.OrderServiceFallback.class)
@Component
public interface OrderService {

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductList
     * @Description: 获取商品列表
     * @date 2018/7/11 16:15
     */
    @GetMapping(value = "order/getProductList")
    Result getProductList();

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量
     * @date 2018/7/11 14:55
     */
    @PostMapping(value = "order/getProductStockById")
    Result getProductStockById(@RequestParam("productId") String productId);

    @Component
    public static class OrderServiceFallback implements OrderService{
        /**
         * @author 刘海洋
         * @version V1.0
         * @Title: getProductList
         * @Description: 获取商品列表
         * @date 2018/7/11 16:15
         */
        @Override
        public Result getProductList() {
            return null;
        }

        /**
         * @param
         * @author 刘海洋
         * @version V1.0
         * @Title: getProductStockById
         * @Description: 根据ID获取某一商品的库存数量
         * @date 2018/7/11 14:55
         */
        @Override
        public Result getProductStockById(String productId) {
            return ResultUtil.setResult(500,"feign使用hystrix返回",null);
        }
    }

}
