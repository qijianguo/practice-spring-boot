package com.qijianguo.service.model;

import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @version 1.0
 * @Title: UserModel
 * @Description: TODO 定义业务模型的实体类
 * @date 2019/2/16 11:05
 */
@Data
public class UserModel {

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telephone;

    private String registerMode;

    private String thirdPartyId;

    private String encrptPassword;

    private String token;

}
