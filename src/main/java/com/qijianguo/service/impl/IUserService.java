package com.qijianguo.service.impl;

import com.qijianguo.controller.paramobject.LoginParams;
import com.qijianguo.dao.UserDoMapper;
import com.qijianguo.dao.UserPasswordDoMapper;
import com.qijianguo.dataobject.UserDo;
import com.qijianguo.dataobject.UserPasswordDo;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.UserService;
import com.qijianguo.service.model.UserModel;
import com.qijianguo.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author Angus
 * @version 1.0
 * @Title: IUserService
 * @Description: TODO
 * @date 2019/2/16 11:00
 */
@Service
public class IUserService implements UserService {

    @Autowired
    private UserDoMapper userDoMapper;
    @Autowired
    private UserPasswordDoMapper userPasswordDoMapper;

    @Override
    public UserModel findUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if (userDo == null) {
            return null;
        }
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(id);
        if (userPasswordDo == null) {
            return null;
        }
        return convertFromDataObject(userDo, userPasswordDo);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // 检查用户是否已经存在
//        UserDo userDo = userDoMapper.selectByTelephone(userModel.getTelephone());
//        if (userDo != null) {
//            throw new BusinessException(EmBusinessError.USER_ALREADY_EXIST);
//        }

        // 保存用户基本信息
        UserDo userDo = convertFromModel(userModel);
        try{
            userDoMapper.insertSelective(userDo);
            userModel.setId(userDo.getId());
        } catch (DuplicateKeyException ex) {
            // 对重复手机号注册抛出异常
            throw new BusinessException(EmBusinessError.USER_ALREADY_EXIST);
        }
        // 保存密码
        UserPasswordDo userPasswordDo = convertPasswordFromModel(userModel);
        userPasswordDoMapper.insertSelective(userPasswordDo);
        // 手动回滚事务
    }

    @Override
    public UserModel validateLogin(LoginParams loginParams) throws BusinessException {
        UserDo userDo = userDoMapper.selectByTelephone(loginParams.getTelephone());
        if (userDo == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(userDo.getId());
        if (userPasswordDo == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        if (!Objects.equals(MD5Util.MD5(loginParams.getEncrptPassword()), userPasswordDo.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserModel userModel = convertFromDataObject(userDo, userPasswordDo);
        return userModel;
    }

    private UserPasswordDo convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPasswordDo userPasswordDo = new UserPasswordDo();
        userPasswordDo.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDo.setUserId(userModel.getId());
        return userPasswordDo;
    }

    private UserDo convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userModel, userDo);
        return userDo;
    }

    private UserModel convertFromDataObject(UserDo userDo, UserPasswordDo userPasswordDo) {
        if (userDo == null || userPasswordDo == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo, userModel);
        userModel.setEncrptPassword(userPasswordDo.getEncrptPassword());
        return userModel;
    }
}
