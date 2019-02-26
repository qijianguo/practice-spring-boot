package com.qijianguo.aop;

import com.qijianguo.annotation.CurrentUser;
import com.qijianguo.commons.Constants;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.model.UserModel;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是AuthenticatedUser并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(UserModel.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserModel userModel = (UserModel) webRequest.getAttribute(Constants.TELPHONE, RequestAttributes.SCOPE_REQUEST);
        if (userModel != null) {
            return userModel;
        }
        throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
    }
}
