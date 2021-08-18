package com.pw.sign.common.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus config
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月18日
 */
@Configuration
public class AliYunSmsConfig {

    @Value("${sms.aliyun.key}")
    private String key;
    @Value("${sms.aliyun.secret}")
    private String secret;
    @Value("${sms.aliyun.endpoint}")
    private String endpoint;

    public static long expire;

    @Value("${sms.aliyun.expire}")
    public void setExpire(long expire) {
        AliYunSmsConfig.expire = expire;
    }

    @Bean(name = "aliYunSms")
    public Client createClient() throws Exception {
        Config config = new Config().setAccessKeyId(key).setAccessKeySecret(secret);
        config.endpoint = endpoint;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}