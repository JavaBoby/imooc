package com.imooc.order.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: OrderDTO
 * @Description: 订单DTO
 * @date 2018/7/30 19:13
 */
@Data
@ToString
public class OrderDTO implements Serializable{

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private String detailId;

    private String productName;

    private String productId;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private List orderDetailList;
}
