package com.qijianguo.controller.viewobject;

import lombok.Data;

/**
 * @author Angus
 * @version 1.0
 * @Title: UserVo
 * @Description: TODO
 * @date 2019/2/16 11:23
 */
@Data
public class UserVo {

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telephone;

    private String token;

}
