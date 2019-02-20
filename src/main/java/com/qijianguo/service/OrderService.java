package com.qijianguo.service;

import com.qijianguo.error.BusinessException;
import com.qijianguo.service.model.OrderInfoModel;

/**
 * @author Angus
 * @version 1.0
 * @Title: OrderService
 * @Description: TODO
 * @date 2019/2/20 15:22
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderInfoModel
     * @return
     */
    OrderInfoModel createOrder(OrderInfoModel orderInfoModel) throws BusinessException;
}
