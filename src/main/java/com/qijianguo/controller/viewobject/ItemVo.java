package com.qijianguo.controller.viewobject;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Angus
 * @version 1.0
 * @Title: ItemModel 商品模型
 * @Description: TODO
 * @date 2019/2/20 9:18
 */
@Data
public class ItemVo {

    private String title;

    private BigDecimal price;

    private Integer stock;

    private Integer sales;

    private String imgUrl;

    private String description;

}
