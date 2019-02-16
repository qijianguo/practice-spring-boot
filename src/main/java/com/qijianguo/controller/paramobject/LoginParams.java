package com.qijianguo.controller.paramobject;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @version 1.0
 * @Title: LoginParams
 * @Description: TODO
 * @date 2019/2/16 16:25
 */
@Data
public class LoginParams {

    @NotNull(message = "手机不能为空")
    @NotEmpty
    private String telephone;

    @NotNull(message = "密码不能为空")
    @NotEmpty
    private String encrptPassword;
}
