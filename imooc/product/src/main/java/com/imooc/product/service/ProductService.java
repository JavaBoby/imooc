package com.imooc.product.service;

import com.imooc.product.dto.ProductDTO;
import com.imooc.product.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductService
 * @Description: 商品服务service接口
 * @date 2018/7/11 15:00
 */
public interface ProductService {

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductList
     * @Description: 获取商品列表
     * @date 2018/7/11 14:55
     */
    Result getProductList();

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量
     * @date 2018/7/11 14:55
     */
    Result getProductStockById(ProductDTO productDTO);
}
