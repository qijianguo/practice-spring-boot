package com.qijianguo.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Angus
 * @version 1.0
 * @Title: OrderInfoModel
 * @Description: TODO 订单
 * @date 2019/2/20 15:15
 */
@Data
public class OrderInfoModel {

    private String id;

    private Integer userId;

    private Integer itemId;

    private BigDecimal itemPrice;

    /**
     * 购买数量
     */
    private Integer amount;

    /**
     * 订单金额
     */
    private BigDecimal orderPrice;

    /**
     * 促销ID
     */
    private Integer promoId;


}
