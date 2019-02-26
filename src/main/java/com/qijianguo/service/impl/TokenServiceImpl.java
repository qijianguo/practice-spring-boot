package com.qijianguo.service.impl;

import com.qijianguo.commons.Constants;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.TokenService;
import com.qijianguo.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 通过Redis存储和验证token的实现类
 */
@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenServiceImpl tokenServiceImpl;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Override
    public String createToken(UserModel user) throws BusinessException {
        if (user == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        //使用uuid作为源token
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        //存储到session/redis并设置过期时间
        httpServletRequest.getSession().setAttribute(Constants.LOGIN_USER, user);
        httpServletRequest.getSession().setAttribute(Constants.IS_LOGIN, true);
        httpServletRequest.getSession().setMaxInactiveInterval(Constants.SESSION_TIMEOUT);
        return token;
    }

    @Override
    public UserModel checkToken(String token) throws BusinessException {
        if (token == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_AUTHORIZATION);
        };
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(Constants.LOGIN_USER);
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute(Constants.IS_LOGIN);
        if (userModel == null || isLogin == null || !isLogin) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        httpServletRequest.getSession().setMaxInactiveInterval(Constants.SESSION_TIMEOUT);
        return userModel;
    }

    @Override
    public void deleteToken(String token) {
        httpServletRequest.getSession().removeAttribute(Constants.IS_LOGIN);
    }
}
