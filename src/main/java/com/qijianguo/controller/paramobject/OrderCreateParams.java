package com.qijianguo.controller.paramobject;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @version 1.0
 * @Title: OrderCreateParams
 * @Description: TODO
 * @date 2019/2/20 15:41
 */
@Data
public class OrderCreateParams {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "商品ID不能为空")
    private Integer itemId;

    @NotNull(message = "商品数量不能为空")
    @Min(value = 0, message = "商品数量不能为0")
    @Max(value = 99, message = "商品数量不能大于99")
    private Integer amount;

}
