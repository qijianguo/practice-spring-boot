package com.qijianguo.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Angus
 * @version 1.0
 * @Title: PromoModel 秒杀模型
 * @Description: TODO
 * @date 2019/2/22 14:10
 */
@Data
public class PromoModel {

    private Integer id;

    private String promoName;

    /**
     * 活动开始时间
     */
    private DateTime startTime;

    private DateTime endTime;

    private Integer itemId;
    /**
     * 秒杀价格
     */
    private BigDecimal promoItemPrice;

    
}
