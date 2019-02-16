package com.qijianguo.controller.paramobject;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @version 1.0
 * @Title: RegisterParams
 * @Description: TODO
 * @date 2019/2/16 14:21
 */
@Data
public class RegisterParams {

    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别不能为空")
    private Byte gender;
    @NotNull(message = "年龄不能为空")
    @Max(value = 150, message = "年龄不合法")
    @Min(value = 0, message = "年龄不合法")
    private Integer age;
    @NotNull(message = "手机号不能为空")
    @NotEmpty(message = "手机号不能为空")
    private String telephone;
    private String registerMode;
    private String thirdPartyId;
    @NotNull
    @NotEmpty(message = "密码不能为空")
    private String encrptPassword;
    @NotNull(message = "验证码不能为空")
    private Integer optCode;
}
