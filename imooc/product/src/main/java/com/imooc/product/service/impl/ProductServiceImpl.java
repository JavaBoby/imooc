package com.imooc.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.product.dto.ProductDTO;
import com.imooc.product.entity.ProductCategory;
import com.imooc.product.entity.ProductInfo;
import com.imooc.product.repository.CategoryMapper;
import com.imooc.product.repository.ProductMapper;
import com.imooc.product.result.Result;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.ResultUtil;
import com.imooc.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductServiceImpl
 * @Description: 商品服务service实现类
 * @date 2018/7/11 15:01
 */
@Service
@Component
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductList
     * @Description: 获取商品列表
     * @date 2018/7/11 14:55
     */
    @Override
    public Result getProductList() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("无参数接口,service层开始获取商品列表.....");
        List<ProductInfo> productInfo = productMapper.findAll();
        log.info("service获取的商品列表是:{}", JSON.toJSONString(productInfo));
        if(!ObjectUtils.isEmpty(productInfo)){
            log.info("获取商品列表完毕,开始获取商品类目列表....");
            List<ProductCategory> productCategoryList = categoryMapper.findAll();
            log.info("service获取的商品类目信息是:{}",JSON.toJSONString(productCategoryList));
            if(!ObjectUtils.isEmpty(productCategoryList)){
                List<ProductVO> productVOS = new ArrayList<ProductVO>(10);
                for (ProductInfo info:productInfo){
                    for (ProductCategory category:productCategoryList){
                        if(info.getCategoryType().equals(category.getCategoryId())){
                            ProductVO productVO = new ProductVO();
                            BeanUtils.copyProperties(info,productVO);
                            productVO.setCategoryName(category.getCategoryName());
                            productVOS.add(productVO);
                        }
                    }
                }
                return ResultUtil.setResult(200,"获取商品列表成功!",productVOS);
            }
        }
        return null;
    }

    /**
     * @author 刘海洋
     * @version V1.0
     * @Title: getProductStockById
     * @Description: 根据ID获取某一商品的库存数量
     * @date 2018/7/11 14:55
     */
    @Override
    public Result getProductStockById(ProductDTO productDTO) {
        log.info("service开始根据ID获取某一商品的库存数量,接收的参数是:{}",JSON.toJSONString(productDTO));
        Result result = null;
       try {
           result = new Result();
           String productStock = productMapper.getProductStockById(productDTO.getProductId());
           log.info("service根据商品ID获取商品的库存数量的结果是:{}",JSON.toJSONString(productStock));
           return ResultUtil.setResult(200,"根据商品ID获取商品的库存数量成功!",productStock);
       }catch (Exception e){
           log.info("service根据商品ID获取商品的库存数量异常!");
           return ResultUtil.error();
       }
    }
}
