

package com.sym.common.validator;

import com.sym.common.exception.CustomException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author suyongming
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CustomException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CustomException(message);
        }
    }
}
