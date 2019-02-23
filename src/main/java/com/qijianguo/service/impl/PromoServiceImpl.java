package com.qijianguo.service.impl;

import com.qijianguo.dao.PromoDoMapper;
import com.qijianguo.dataobject.PromoDo;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Angus
 * @version 1.0
 * @Title: PromoServiceImpl
 * @Description: TODO
 * @date 2019/2/22 15:03
 */
@Service
public class PromoServiceImpl implements PromoService{

    @Autowired
    private PromoDoMapper promoDoMapper;

    @Override
    public PromoDo selectByItemId(Integer itemId) throws BusinessException {
        PromoDo promoDo = promoDoMapper.selectByItemId(itemId);
        if (promoDo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "未查询到促销信息");
        }
        return promoDo;
    }
}
