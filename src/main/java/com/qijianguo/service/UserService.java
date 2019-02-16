package com.qijianguo.service;

import com.qijianguo.controller.paramobject.LoginParams;
import com.qijianguo.error.BusinessException;
import com.qijianguo.service.model.UserModel;

/**
 * @author Angus
 * @version 1.0
 * @Title: UserService
 * @Description: TODO
 * @date 2019/2/16 10:59
 */
public interface UserService {

    UserModel findUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(LoginParams loginParams) throws BusinessException;
}
