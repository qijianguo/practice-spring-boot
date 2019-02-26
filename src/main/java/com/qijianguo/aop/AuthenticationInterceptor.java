package com.qijianguo.aop;

import com.qijianguo.annotation.Authentication;
import com.qijianguo.commons.Constants;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.TokenService;
import com.qijianguo.service.UserService;
import com.qijianguo.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 */
@Component
@Slf4j
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果未注明Authenticatoin
        if (method.getAnnotation(Authentication.class) == null) {
            return true;
        }

        // 从header中得到token
        String token = request.getHeader(Constants.TOKEN);
        UserModel userModel = tokenService.checkToken(token);

        // 验证该用户是否已登录

        //如果token验证成功，将token对应的用户存在request中，便于之后注入
        request.setAttribute(Constants.TELPHONE, userModel);
        return true;
    }
    
}
