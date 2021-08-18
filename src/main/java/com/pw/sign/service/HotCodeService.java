package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.SignHotCode;

/**
 * 热配服务
 */
public interface HotCodeService extends IService<SignHotCode> {
    int overwrite(String key, String type, String info);

    SignHotCode getOneByKeyType(String phone, String type);
}
