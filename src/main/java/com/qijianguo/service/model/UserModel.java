package com.qijianguo.service.model;

import com.qijianguo.service.RedisEntity;
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
public class UserModel implements RedisEntity {

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telephone;

    private String registerMode;

    private String thirdPartyId;

    private String encrptPassword;

    private String token;

    @Override
    public String generateRedisKey() {
        return String.format("user_info:%s:name", name);
    }

    @Override
    public String generateRedisKey(Object key) {
        return String.format("user_info:%d:name", key);
    }

}
