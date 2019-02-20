package com.qijianguo.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Angus
 * @version 1.0
 * @Title: ValidatorImpl
 * @Description: TODO
 * @date 2019/2/20 10:00
 */
@Component
public class ValidatorImpl implements InitializingBean{

    @Autowired
    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ValidationResult validatate(Object bean) {
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> set = validator.validate(bean);
        if (set.size() > 0) {
            set.forEach(objectConstraintViolation -> {
                String errorMessage = objectConstraintViolation.getMessage();
                String propertyName = objectConstraintViolation.getPropertyPath().toString();
                validationResult.getErrorMessageMap().put(propertyName, errorMessage);
            });
        }
        return validationResult;
    }
}
