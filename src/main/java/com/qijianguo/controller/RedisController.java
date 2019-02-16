package com.qijianguo.controller;

import com.qijianguo.commons.ExceptionEnum;
import com.qijianguo.dao.UserDoMapper;
import com.qijianguo.dataobject.Result;
import com.qijianguo.dataobject.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @version 1.0
 * @Title: RedisController
 * @Description: TODO
 * @date 2019/2/16 9:29
 */
@RestController
public class RedisController {

    @Autowired
    private UserDoMapper userDoMapper;

    @GetMapping("/redis")
    public Result test() {
        UserDo user = userDoMapper.selectByPrimaryKey(1);
        if(user == null) {
            return Result.fail(ExceptionEnum._400.getCode(), "用户不存在");
        }
        return Result.success();
    }


}
