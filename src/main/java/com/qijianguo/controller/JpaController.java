package com.qijianguo.controller;

import com.qijianguo.dataobject.JpaUser;
import com.qijianguo.dataobject.Result;
import com.qijianguo.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @version 1.0
 * @Title: JpaController
 * @Description: TODO
 * @date 2019/2/14 13:49
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaUserService jpaUserService;

    @PostMapping("/save")
    public Result save(JpaUser jpaUser) {
        jpaUserService.save(jpaUser);
        return Result.success(jpaUser);
    }

}
