package com.qijianguo.controller;

import com.qijianguo.controller.paramobject.ItemCreateParams;
import com.qijianguo.controller.viewobject.ItemVo;
import com.qijianguo.dataobject.Result;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.ItemService;
import com.qijianguo.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Angus
 * @version 1.0
 * @Title: ItemController
 * @Description: TODO
 * @date 2019/2/20 10:58
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public Result createItem(@Valid ItemCreateParams itemCreateParams) throws BusinessException {
        ItemModel itemModel = convertItemModelFromParams(itemCreateParams);
        ItemModel createModelFromResult = itemService.createItem(itemModel);
        ItemVo itemVo = convertItemVoFromModel(createModelFromResult);
        return Result.success(itemVo);
    }

    @GetMapping("/list")
    public Result getListItem() throws BusinessException {
        List<ItemModel> list = itemService.listItem();
        List<ItemVo> itemVoList = convertItemVoFromModelList(list);
        return Result.success(itemVoList);
    }

    @GetMapping("/{id}")
    public Result getItem(@PathVariable(value = "id") Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVo itemVo = convertItemVoFromModel(itemModel);
        return Result.success(itemVo);
    }

    private ItemModel convertItemModelFromParams(ItemCreateParams itemCreateParams) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemCreateParams, itemModel);
        return itemModel;
    }

    private ItemVo convertItemVoFromModel(ItemModel itemModel) throws BusinessException {
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.NOT_FOUND_RESOURCE);
        }
        ItemVo itemVo = new ItemVo();
        BeanUtils.copyProperties(itemModel, itemVo);
        return itemVo;
    }

    private List<ItemVo> convertItemVoFromModelList(List<ItemModel> itemModels) throws BusinessException {
        if (Optional.ofNullable(itemModels).orElse(Collections.EMPTY_LIST).size() == 0) {
            throw new BusinessException(EmBusinessError.NOT_FOUND_RESOURCE);
        }
        return itemModels.stream().map(itemModel -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(itemModel, itemVo);
            return itemVo;
        }).collect(Collectors.toList());
    }
}
