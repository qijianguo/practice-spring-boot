package com.qijianguo.controller;

import com.qijianguo.dataobject.Result;
import com.qijianguo.dataobject.JpaUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Angus
 * @version 1.0
 * @Title: BeanValidationController
 * @Description: TODO
 * @date 2019/2/13 15:10
 */
@RestController
@RequestMapping("/validator")
public class BeanValidationController {

    @PostMapping("/simple")
    public Result simple(@Valid JpaUser jpaUser) {
        System.out.println(jpaUser);
        return Result.success(jpaUser);
    }

}
