package com.imooc.product.repository;

import com.imooc.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductMapper
 * @Description: 商品服务数据交互层mapper接口
 * @date 2018/7/11 15:03
 */
@Component
public interface ProductMapper extends JpaRepository<ProductInfo,String>{

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量
     * @date 2018/7/11 14:55
     */
    @Query("select a.productStock from ProductInfo a where a.productId = ?1")
    String getProductStockById(String productId);

    @Query("select a.productName,a.productPrice,a.productStock,a.productStatus from ProductInfo a where a.productId = ?1")
    ProductInfo findProductMessage(String productId);
}
