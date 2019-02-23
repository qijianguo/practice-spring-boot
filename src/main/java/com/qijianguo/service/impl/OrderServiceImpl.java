package com.qijianguo.service.impl;

import com.qijianguo.controller.paramobject.OrderCreateParams;
import com.qijianguo.dao.OrderInfoDoMapper;
import com.qijianguo.dao.PromoDoMapper;
import com.qijianguo.dao.SequenceInfoDoMapper;
import com.qijianguo.dataobject.OrderInfoDo;
import com.qijianguo.dataobject.PromoDo;
import com.qijianguo.dataobject.SequenceInfoDo;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.ItemService;
import com.qijianguo.service.OrderService;
import com.qijianguo.service.PromoService;
import com.qijianguo.service.UserService;
import com.qijianguo.service.model.ItemModel;
import com.qijianguo.service.model.OrderInfoModel;
import com.qijianguo.service.model.UserModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Angus
 * @version 1.0
 * @Title: OrderServiceImpl
 * @Description: TODO
 * @date 2019/2/20 15:40
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderInfoDoMapper orderInfoDoMapper;
    @Autowired
    private SequenceInfoDoMapper sequenceInfoDoMapper;
    @Autowired
    private PromoService promoService;
    @Autowired
    private PromoDoMapper promoDoMapper;
    @Override
    public OrderInfoModel createOrder(OrderInfoModel orderInfoModel) throws BusinessException {
        // 校验参数
        validator(orderInfoModel);
        OrderInfoDo orderInfoDo = convertFromOrderModel(orderInfoModel);
        orderInfoDoMapper.insertSelective(orderInfoDo);
        orderInfoModel.setId(orderInfoDo.getId());
        return orderInfoModel;
    }

    private void validator(OrderInfoModel orderInfoModel) throws BusinessException {
        // 1. 用户是否存在、 商品是否存在、 是否是上架状态。。
        UserModel userModel = userService.findUserById(orderInfoModel.getUserId());
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        ItemModel itemModel = itemService.getItemById(orderInfoModel.getItemId());
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        // 2. 修改库存
        boolean result = itemService.decreaseStock(orderInfoModel.getItemId(), orderInfoModel.getAmount());
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        // 3. 修改销量
        boolean result2 = itemService.increaseSales(orderInfoModel.getItemId(), orderInfoModel.getAmount());
        if (!result2) {
            throw new BusinessException(EmBusinessError.UNKNOW_ERROR, "销量保存错误");
        }
        // 4. 查看是否促销
        if (orderInfoModel.getPromoId() != null) {
            PromoDo promoDo = promoDoMapper.selectByPrimaryKey(orderInfoModel.getPromoId());
            if (!Objects.equals(orderInfoModel.getItemId(), promoDo.getItemId())) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品无活动");
            }
            DateTime now = new DateTime();
            if (now.isBefore(promoDo.getStartTime().getTime())) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动未开始");
            }
            if (new DateTime(promoDo.getEndTime()).isBeforeNow()) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动已结束");
            }
            orderInfoModel.setPromoId(orderInfoModel.getPromoId());
            orderInfoModel.setOrderPrice(promoDo.getPromoItemPrice());
            orderInfoModel.setOrderPrice(promoDo.getPromoItemPrice().multiply(new BigDecimal(orderInfoModel.getAmount())));
        } else {
            orderInfoModel.setAmount(orderInfoModel.getAmount());
            orderInfoModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(orderInfoModel.getAmount())));
        }

        // 设置订单ID：20190220 123456 01
        orderInfoModel.setUserId(orderInfoModel.getUserId());
        orderInfoModel.setItemId(orderInfoModel.getItemId());
        orderInfoModel.setItemPrice(itemModel.getPrice());

    }

    private OrderInfoDo convertFromOrderModel(OrderInfoModel orderInfoModel) {
        if (orderInfoModel == null) {
            return null;
        }
        OrderInfoDo orderInfoDo = new OrderInfoDo();
        BeanUtils.copyProperties(orderInfoModel, orderInfoDo);
        // 设置订单ID
        String orderId = generateOrderNo(orderInfoModel);
        orderInfoDo.setId(orderId);
        return orderInfoDo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo(OrderInfoModel orderInfoModel) {
        StringBuffer sb = new StringBuffer();
        // 业务： 16位
        // 前八位：时间yyyyMMdd
        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        sb.append(now);
        // 中6位：自增序列
        SequenceInfoDo sequenceInfoDo = sequenceInfoDoMapper.selectByName("order_info");
        int sequence = sequenceInfoDo.getCurrentValue();
        // 如果sequence超过最大值，则重置sequence（暂时不实现）
        sequenceInfoDo.setCurrentValue(sequence + sequenceInfoDo.getStep());
        sequenceInfoDoMapper.updateByPrimaryKeySelective(sequenceInfoDo);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            sb.append(0);
        }
        sb.append(sequenceStr);
        // 末2位：分库分表位 暂时写死
        sb.append("00");
        return sb.toString();
    }
}
