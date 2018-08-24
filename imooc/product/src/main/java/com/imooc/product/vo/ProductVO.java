package com.imooc.product.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductVO
 * @Description: 商品VO
 * @date 2018/7/11 15:55
 */
@Data
@ToString
public class ProductVO {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Long productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date update_time;

    private Integer categoryId;

    private String categoryName;
}
