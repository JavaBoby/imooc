package com.imooc.product.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ProductCategory
 * @Description: 商品类目实体类
 * @date 2018/7/11 15:12
 */
@Data
@ToString
@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_type")
    private Integer categoryType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date update_time;
}
