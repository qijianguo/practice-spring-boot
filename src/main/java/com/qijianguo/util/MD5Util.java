package com.qijianguo.util;

import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;

/**
 * @author Angus
 * @version 1.0
 * @Title: MD5Util
 * @Description: TODO
 * @date 2019/2/16 16:47
 */
public class MD5Util {

    public static String MD5(String key) {
        return MD5Encoder.encode(ConcurrentMessageDigest.digestMD5(
                key.getBytes()));
    }
}
