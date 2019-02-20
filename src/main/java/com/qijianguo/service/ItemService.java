package com.qijianguo.service;

import com.qijianguo.error.BusinessException;
import com.qijianguo.service.model.ItemModel;

import java.util.List;

/**
 * @author Angus
 * @version 1.0
 * @Title: ItemService
 * @Description: TODO
 * @date 2019/2/20 10:19
 */
public interface ItemService {

    /**
     * 保存商品信息
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * 查询商品列表
     */
    List<ItemModel> listItem() throws BusinessException;

    /**
     * 查询单个商品
     */
    ItemModel getItemById(Integer id) throws BusinessException;

    /**
     * 减少库存
     * @param itemId
     * @param amount
     * @return
     */
    boolean decreaseStock(Integer itemId, Integer amount);

    /**
     * 增加销量
     * @param itemId
     * @param amount
     * @return
     */
    boolean increaseSales(Integer itemId, Integer amount);

}
