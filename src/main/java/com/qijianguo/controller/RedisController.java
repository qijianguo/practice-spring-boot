package com.qijianguo.controller;

import com.qijianguo.commons.ExceptionEnum;
import com.qijianguo.dao.UserDoMapper;
import com.qijianguo.dataobject.Result;
import com.qijianguo.dataobject.UserDo;
import com.qijianguo.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @version 1.0
 * @Title: RedisController
 * @Description: TODO
 * @date 2019/2/16 9:29
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/value")
    public Result saveValue() {
        String key = "name";
        String value = "Angus";
        stringRedisTemplate.opsForValue().set(key, value);

        String result = stringRedisTemplate.opsForValue().get(key);

        return Result.success(key + ":" + result);
    }

    @GetMapping("/bean")
    public Result saveUser() {
        UserModel userModel = new UserModel();
        userModel.setName("张三");
        UserModel userModel2 = new UserModel();
        userModel2.setName("李四");

        ListOperations valueOperations = redisTemplate.opsForList();
        valueOperations.leftPush("user", userModel);
        valueOperations.leftPush("user", userModel2);

        List<UserModel> userModelList = redisTemplate.opsForList().range(userModel.getName(), 1, -1);

        redisTemplate.opsForValue().set(userModel.generateRedisKey(), userModel);
        redisTemplate.opsForValue().set(userModel2.generateRedisKey(), userModel2);
        UserModel userModel1 = (UserModel) redisTemplate.opsForValue().get(userModel.generateRedisKey());
        return Result.success(userModel1);
    }


}
