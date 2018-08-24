package com.imooc.product.repository;

import com.imooc.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: CategoryMapper
 * @Description: 商品类目数据交互mapper
 * @date 2018/7/11 15:47
 */
@Component
public interface CategoryMapper extends JpaRepository<ProductCategory,Integer>{
}
