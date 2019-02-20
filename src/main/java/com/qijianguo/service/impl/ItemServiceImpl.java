package com.qijianguo.service.impl;

import com.qijianguo.dao.ItemDoMapper;
import com.qijianguo.dao.ItemStockDoMapper;
import com.qijianguo.dataobject.ItemDo;
import com.qijianguo.dataobject.ItemStockDo;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.ItemService;
import com.qijianguo.service.model.ItemModel;
import com.qijianguo.validator.ValidationResult;
import com.qijianguo.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Angus
 * @version 1.0
 * @Title: ItemServiceImpl
 * @Description: TODO
 * @date 2019/2/20 10:24
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDoMapper itemDoMapper;
    @Autowired
    private ItemStockDoMapper itemStockDoMapper;
    @Autowired
    private ValidatorImpl validator;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // 校验参数
        ValidationResult validationResult = validator.validatate(itemModel);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrorMessage());
        }
        // 将model -> DO
        ItemDo itemDo = convertItemDoFromItemModel(itemModel);
        // 将商品持久化到DB
        itemDoMapper.insertSelective(itemDo);
        itemModel.setId(itemDo.getId());
        // 将库存持久化到DB
        itemStockDoMapper.insertSelective(convertItemStockFromItemModel(itemModel));
        // 返回结果
        return getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() throws BusinessException {
        List<ItemDo> itemDoList = itemDoMapper.listItem();
        if (Optional.ofNullable(itemDoList).orElse(Collections.emptyList()).size() == 0){
            throw new BusinessException(EmBusinessError.NOT_FOUND_RESOURCE);
        }
        List<ItemModel> itemModelList = itemDoList.stream().map(itemDo -> {
            ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
            ItemModel itemModel = convertItemModelFromDataObject(itemDo, itemStockDo);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) throws BusinessException {
        ItemDo itemDo = itemDoMapper.selectByPrimaryKey(id);
        // 获取库存
        ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(id);
        ItemModel itemModel = convertItemModelFromDataObject(itemDo, itemStockDo);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRows = itemStockDoMapper.decreaseStock(amount, itemId);
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean increaseSales(Integer itemId, Integer amount) {
        int affectedRows = itemDoMapper.increaseSales(itemId, amount);
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }

    private ItemDo convertItemDoFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDo itemDo = new ItemDo();
        BeanUtils.copyProperties(itemModel, itemDo);
        return itemDo;
    }

    private ItemStockDo convertItemStockFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDo itemStockDo = new ItemStockDo();
        itemStockDo.setItemId(itemModel.getId());
        itemStockDo.setStock(itemModel.getStock());
        return itemStockDo;
    }

    private ItemModel convertItemModelFromDataObject(ItemDo itemDo, ItemStockDo itemStockDo) {
        if (itemDo == null) {
            return null;
        }
        if (itemStockDo == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDo, itemModel);
        // TODO: 处理price
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;
    }

}
