package com.qijianguo.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Angus
 * @version 1.0
 * @Title: ValidationResult
 * @Description: TODO
 * @date 2019/2/20 9:04
 */
@Data
public class ValidationResult {

    private boolean hasErrors = false;

    private Map<String, String> errorMessageMap = new HashMap<>(16);

    public String getErrorMessage() {
        return StringUtils.join(errorMessageMap.values().toArray(), ",");
    }

}
