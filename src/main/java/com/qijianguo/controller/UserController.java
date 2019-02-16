package com.qijianguo.controller;

import com.qijianguo.controller.paramobject.LoginParams;
import com.qijianguo.controller.paramobject.RegisterParams;
import com.qijianguo.controller.viewobject.UserVo;
import com.qijianguo.dataobject.Result;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import com.qijianguo.service.UserService;
import com.qijianguo.service.model.UserModel;
import com.qijianguo.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Random;

/**
 * @author Angus
 * @version 1.0
 * @Title: UserController
 * @Description: TODO
 * @date 2019/2/16 10:56
 */
@RestController
@RequestMapping("/user")
// 解决跨域 defalt_allowed_headers允许跨域传输所有的header请求，将使用将token放入header域做session共享的跨域请求
// 但session是没法对ajax共享的
//@CrossOrigin
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
// 注意：前端需要做跨域授信：xhrFields:{withCredentials:true}
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/opt")
    public Result getOpt(String telephone) {
        // 校验电话号
        // 随机生成数字
        Random random = new Random();
        Integer randomInt = random.nextInt(999999);
        randomInt += 100000;

        // 手机号与opt关联(一般用redis)
        // 模拟存储在session中
        httpServletRequest.getSession().setAttribute(telephone, randomInt);
        log.debug("tel:{}，passwd: {}", telephone, randomInt);
        // 发送给用户
        return Result.success("验证码已发送到您手机，请注意查收" + randomInt);
    }

    @PostMapping("/register")
    public Result registe(@Valid RegisterParams params) throws BusinessException {
        // 校验手机和验证码是否匹配
        Integer optCode = (Integer) httpServletRequest.getSession().getAttribute(params.getTelephone());
        if (!Objects.equals(params.getOptCode(), optCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码有误");
        }
        // 注册
        userService.register(convertFromParams(params));

        return Result.success();
    }

    public Result login(@Valid LoginParams params) throws BusinessException {
        // 用户校验是否合法
        UserModel userModel =  userService.validateLogin(params);
        //保存token或session
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        UserVo userVo = convertFromModel(userModel);
        return Result.success(userVo);
    }

    @GetMapping()
    public Result getUser(Integer id) throws Exception {
        UserModel userModel = userService.findUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return Result.success(convertFromModel(userModel));
    }

    private UserModel convertFromParams(RegisterParams params) {
        if (params == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(params, userModel);
        // 对密码进行MD加密
        userModel.setEncrptPassword(MD5Util.MD5(params.getEncrptPassword()));
        return userModel;
    }

    private UserVo convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel, userVo);
        return userVo;
    }

}
