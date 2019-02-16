package com.qijianguo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper ObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper()
//                .registerModule(new ParameterNamesModule())
//                .registerModule(new Jdk8Module())
//                .registerModule(new JavaTimeModule());
//        return mapper;

        // 自动注册
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

}