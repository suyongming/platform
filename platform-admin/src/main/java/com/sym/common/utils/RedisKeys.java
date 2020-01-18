

package com.sym.common.utils;

/**
 * Redis所有Keys
 *
 * @author suyongming
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
