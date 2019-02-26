package com.qijianguo.controller;

import com.qijianguo.annotation.Authentication;
import com.qijianguo.annotation.CurrentUser;
import com.qijianguo.controller.paramobject.OrderCreateParams;
import com.qijianguo.dataobject.Result;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.OrderService;
import com.qijianguo.service.model.OrderInfoModel;
import com.qijianguo.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Angus
 * @version 1.0
 * @Title: OrderController
 * @Description: TODO
 * @date 2019/2/20 16:54
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/save")
    @Authentication
    public Result createOrder(@CurrentUser UserModel userModel, @Valid OrderCreateParams orderCreateParams) throws BusinessException {
        orderCreateParams.setUserId(userModel.getId());
        OrderInfoModel orderInfoModel = convertFromOrderParams(orderCreateParams);
        OrderInfoModel orderInfoModelForResult = orderService.createOrder(orderInfoModel);
        return Result.success();
    }

    private OrderInfoModel convertFromOrderParams(OrderCreateParams orderCreateParams) {
        OrderInfoModel orderInfoModel =  new OrderInfoModel();
        BeanUtils.copyProperties(orderCreateParams, orderInfoModel);
        return orderInfoModel;
    }

}
