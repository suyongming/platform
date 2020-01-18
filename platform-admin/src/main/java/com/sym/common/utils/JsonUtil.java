package com.sym.common.utils;
import java.util.Map;
import java.util.HashMap;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author suyongming
 * @date 创建时间：2019/8/30 14:00
 */
public class JsonUtil {

    static ObjectMapper objectMapper;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 允许出现特殊字符和转义符
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        // 允许出现单引号
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
    }
    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。<br/>
     * (1)转换为普通JavaBean：readValue(json,Student.class)<br/>
     * (2)转换为List:readValue(json,List.class).但是如果我们想把json转换为特定类型的List，比如List<Student>，就不能直接进行转换了。
     * 因为readValue(json,List.class)返回的其实是List<Map>类型，你不能指定readValue()的第二个参数是List<Student>.class，所以不能直接转换。
     * 我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List。<br/>
     *  (3)转换为Map：readValue(json,Map.class) 我们使用泛型，得到的也是泛型<br/>
     * @param content 要转换的JavaBean类型
     * @param valueType 原始json字符串数据
     * @return JavaBean对象
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(valueType)) {
            return null;
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            return null;
        }
    }
}

