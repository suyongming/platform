

package com.sym.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author suyongming
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
