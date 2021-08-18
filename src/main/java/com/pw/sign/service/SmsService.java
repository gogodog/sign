package com.pw.sign.service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;

/**
 * 短信服务
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月17日
 */
public interface SmsService {
    boolean sendAuthCodeByAliYun(String phone);
    boolean checkAuthCodeByAliYun(String phone, String code);
}
