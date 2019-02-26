package com.qijianguo.service;

import com.qijianguo.error.BusinessException;
import com.qijianguo.service.model.UserModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenService {

    /**
     * 创建一个token关联上指定用户
     * @param user 用户
     * @return 生成的token
     */
    String createToken(UserModel user) throws BusinessException;

    /**
     * 检查token是否有效
     * @param token 登录用户的token
     * @return 有效返回用户, 无效则返回null
     */
     UserModel checkToken(String token) throws BusinessException;

    /**
     * 清除token
     * @param token 登录用户的token
     */
    void deleteToken(String token);

}