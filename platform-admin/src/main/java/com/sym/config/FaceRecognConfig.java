package com.sym.config;

/**
 * @author suyongming
 * @date 创建时间：2019/8/30 13:46
 */
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class FaceRecognConfig {
    @Value("${ali.accessKeyID}")
    private String ak_id; //用户ak
    @Value("${ali.accessKeySecret}")
    private String ak_secret;// 用户ak_secret
    @Value("${ali.verifyUrl}")
    private String verifyUrl;// 人脸对比API接口调用地址
    @Value("${ali.detectUrl}")
    private String detectUrl;// 人脸检测API接口调用地址
    @Value("${ali.attributeUrl}")
    private String attributeUrl;// 人脸属性检测API接口调用地址
}
