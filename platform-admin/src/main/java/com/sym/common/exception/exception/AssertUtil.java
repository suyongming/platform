package com.sym.common.exception.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @description: 通用断言
 * @author: mxt
 * @create: 2021-01-11 18:23
 */
public class AssertUtil {

    public static final String REGEX_MOBILE = "^1[0-9]{10}$";

    public static void successLine(int line) {
        if (line <= 0) {
            throw BaseCustomException.getInstance("数据操作异常");
        }
    }

    public static void error(String msg) {
        throw BaseCustomException.getInstance(msg);
    }

    public static void successLine(int line, String... msgs) {
        String msg = replaceMsg(msgs);
        if (line <= 0) {
            throw BaseCustomException.getInstance(msg);
        }
    }

    private static String replaceMsg(String... msgs) {
        String msg = null;
        if (msgs != null && msgs.length > 0) {
            msg = msgs[0];
            for (int i = 1; i < msgs.length; i++) {
                msg = msg.replaceFirst("\\{\\}", msgs[i]);
            }
        }
        return msg;
    }

    public static void isMobile(String mobile, String... msgs) {
        if (!mobile.matches(REGEX_MOBILE)) {
            String msg = replaceMsg(msgs);
            throw BaseCustomException.getInstance(msg);
        }
    }

    public static void isTrue(boolean o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (!o) {
            throw BaseCustomException.getInstance(msg);
        }
    }

    public static void isFalse(boolean o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o) {
            throw BaseCustomException.getInstance(msg);
        }
    }

    public static void isNull(Object o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o != null) {
            throw BaseCustomException.getInstance(msg);
        }
    }

    public static void notNull(Object o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o == null) {
            throw BaseCustomException.getInstance(msg);
        }
    }
    /**
     * 判断一个Number是正数
     *
     * @param o
     */
    public static void isPositive(Object o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o == null) {
            throw BaseCustomException.getInstance(msg);
        }
        if (o instanceof Number) {
            if (o instanceof Integer) {
                Integer integer = (Integer) o;
                if (integer <= 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
            if (o instanceof Double) {
                Double d = (Double) o;
                if (d <= 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
            if (o instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) o;
                if (bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
        } else {
            throw BaseCustomException.getInstance(msg);
        }
    }

    /**
     * 判断一个Number是正数
     *
     * @param o
     */
    public static void isPositiveOrZero(Object o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o == null) {
            throw BaseCustomException.getInstance(msg);
        }
        if (o instanceof Number) {
            if (o instanceof Integer) {
                Integer integer = (Integer) o;
                if (integer < 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
            if (o instanceof Double) {
                Double d = (Double) o;
                if (d < 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
            if (o instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) o;
                if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
        } else {
            throw BaseCustomException.getInstance(msg);
        }
    }

    public static void isEmpty(Object o, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o != null) {
            if (o instanceof String) {
                String o1 = (String) o;
                if (!o1.equals("")) {
                    throw BaseCustomException.getInstance(msg);
                }
            }else if (o instanceof Collection) {
                Collection o1 = (Collection) o;
                if (!CollectionUtils.isEmpty(o1)) {
                    throw BaseCustomException.getInstance(msg);
                }
            } else if (o instanceof Map) {
                Map o1 = (Map) o;
                if (!CollectionUtils.isEmpty(o1)) {
                    throw BaseCustomException.getInstance(msg);
                }
            } else {
                if (o != null) {
                    throw BaseCustomException.getInstance(msg);
                }
            }
        }
    }

    public static void notEmpty(Object o, String name, Integer minLength, Integer maxLength, String... msgs) {
        String msg = replaceMsg(msgs);
        if (o == null) {
            if (msg != null) {
                throw BaseCustomException.getInstance(msg);
            } else {
                throw BaseCustomException.getInstance(name + "不能为空");
            }
        }
        if (o instanceof String) {
            String o1 = (String) o;
            if (o1.equals("")) {
                if (msg != null) {
                    throw BaseCustomException.getInstance(msg);
                } else {
                    throw BaseCustomException.getInstance(name + "不能为空");
                }
            }
            if (maxLength != null) {
                if (StringUtils.isNotEmpty(name)) {
                    msg = replaceMsg("{}长度不能超过{}", name, maxLength.toString());
                }
                isTrue(o1.length() <= maxLength, msg);
            }
            if (minLength != null) {
                if (StringUtils.isNotEmpty(name)) {
                    msg = replaceMsg("{}长度不能低于{}", name, minLength.toString());
                }
                isTrue(o1.length() >= minLength, "长度不能低于{}", msg);
            }
        }
        if (o instanceof Collection) {
            Collection o1 = (Collection) o;
            if (CollectionUtils.isEmpty(o1)) {
                if (msg != null) {
                    throw BaseCustomException.getInstance(msg);
                } else {
                    throw BaseCustomException.getInstance(name + "不能为空");
                }
            }
            if (maxLength != null) {
                if (StringUtils.isNotEmpty(name)) {
                    msg = replaceMsg("{}长度不能超过{}", name, maxLength.toString());
                }
                isTrue(o1.size() <= maxLength, msg);
            }
            if (minLength != null) {
                if (StringUtils.isNotEmpty(name)) {
                    msg = replaceMsg("{}长度不能低于{}", name, minLength.toString());
                }
                isTrue(o1.size() >= minLength, "长度不能低于{}", msg);
            }
        }
        if (o instanceof Map) {
            Map o1 = (Map) o;
            if (CollectionUtils.isEmpty(o1)) {
                if (msg != null) {
                    throw BaseCustomException.getInstance(msg);
                } else {
                    throw BaseCustomException.getInstance(name + "不能为空");
                }
            }
        }
    }

    public static void notEmpty(Object o, String... msgs) {
        notEmpty(o, null, null, null, msgs);
    }

}
