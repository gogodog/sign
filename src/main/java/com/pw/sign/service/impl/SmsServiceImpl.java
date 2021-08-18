package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.common.config.AliYunSmsConfig;
import com.pw.sign.common.utils.NumUtils;
import com.pw.sign.entity.SignHotCode;
import com.pw.sign.entity.SignSign;
import com.pw.sign.enums.Anonymous;
import com.pw.sign.enums.HotCodeType;
import com.pw.sign.mapper.SignSignMapper;
import com.pw.sign.service.HotCodeService;
import com.pw.sign.service.SignService;
import com.pw.sign.service.SmsService;
import com.pw.sign.vo.IdeaSignVoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月17日
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource(name = "aliYunSms")
    private Client aliYunSmsClient;
    @Resource
    private HotCodeService hotCodeService;

    @Override
    public boolean sendAuthCodeByAliYun(String phone) {
        //TODO 等域名备案成功再获取签名和模板进行发送
        String code = NumUtils.generateVerificationCode();
        log.info("身份验证码{},{}", phone, code);
        //发送完之后存储在数据库中
        int i = hotCodeService.overwrite(phone, HotCodeType.AUTHCODE.getCode(), code);
        return i == 1;
    }

    @Override
    public boolean checkAuthCodeByAliYun(String phone, String code) {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        SignHotCode hotCode = this.hotCodeService.getOneByKeyType(phone, HotCodeType.AUTHCODE.getCode());
        return hotCode != null && code.equals(hotCode.getInfo()) && (System.currentTimeMillis() - hotCode.getCreateTime().getTime() <= AliYunSmsConfig.expire * 1000);
    }
}
