package com.imooc.order.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderDetail
 * @Description: 订单详情实体类
 * @date 2018/7/30 19:20
 */
@Data
@ToString
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @Column(name = "detail_id")
    private String detailId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "product_icon")
    private String productIcon;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
