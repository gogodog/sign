package com.pw.sign.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.exception.BusinessException;
import com.pw.sign.common.exception.code.BaseResponseCode;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.vo.UserVo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Secret {

    public static final String ALGORITHM_MD5 = "md5";

    public static UserVo userInfoForCookie(SysXUser sysXUser) {
        try {
            String md5 = md5Encrypt(JSONObject.toJSONString(sysXUser));
            return new UserVo(sysXUser, md5);
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 secret error {}", e);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
    }

    public static String md5Encrypt(final String content) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(ALGORITHM_MD5);
        BigInteger digest = new BigInteger(md5.digest(content.getBytes()));
        return digest.toString(16);
    }

}
