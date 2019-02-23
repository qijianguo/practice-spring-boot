package com.qijianguo.service;

import com.qijianguo.dataobject.PromoDo;
import com.qijianguo.error.BusinessException;

/**
 * @author Angus
 * @version 1.0
 * @Title: PromoService
 * @Description: TODO
 * @date 2019/2/22 15:02
 */
public interface PromoService {

    PromoDo selectByItemId(Integer itemId) throws BusinessException;
}
