package com.imooc.product.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductInfo
 * @Description: 商品实体类
 * @date 2018/7/11 15:06
 */
@Data
@ToString
@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_stock")
    private Integer productStock;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_icon")
    private String productIcon;

    @Column(name = "product_status")
    private Long productStatus;

    @Column(name = "category_type")
    private Integer categoryType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date update_time;
}
