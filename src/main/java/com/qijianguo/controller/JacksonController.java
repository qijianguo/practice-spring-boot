package com.qijianguo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qijianguo.dataobject.JpaUser;
import com.qijianguo.dataobject.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Angus
 * @date 2019/2/13 13:27
 * @Desucription: 优雅的使用Jackson
 */
@RestController
@RequestMapping("/jackson")
public class JacksonController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/simple")
    public Result sampleMethods() {
        Map<String, Object> map = new HashMap<>();
        map.put("encrptPassword", "123456");
        map.put("username", "admin");
        map.put("age", 1233);
        map.put("time", "2019-01-01 12:00:01");
        // 忽略Json字符串中不识别的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // String -> Object
        JpaUser str2Obj = null;
        try {
            String jsonStr = "{\"encrptPassword\":\"123456\",\"username\":\"admin\"}";
            str2Obj = mapper.readValue(jsonStr, JpaUser.class);
            System.out.println(str2Obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Object -> Object
        JpaUser obj2Obj = mapper.convertValue(map, JpaUser.class);
        System.out.println(obj2Obj);

        // Object  -> String
        try {
            String jsonStr = mapper.writeValueAsString(obj2Obj);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<JpaUser> jpaUsers = Arrays.asList(str2Obj, obj2Obj);
        try {
            // Object -> String
            String listStr = mapper.writeValueAsString(jpaUsers);

            // Json -> List
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, JpaUser.class);

            List<JpaUser> str2List =mapper.readValue(listStr, javaType);
            System.out.println(str2List);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success();
    }


}
