package com.pw.sign.controller.helper;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.utils.Constant;
import com.pw.sign.common.utils.CookieUtils;
import com.pw.sign.common.utils.Secret;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.vo.UserVo;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class NetUserControllerHelper {
    public static void secretAndSetCookie(SysXUser sysXUser, HttpServletResponse response) throws UnsupportedEncodingException {
        UserVo vo = Secret.userInfoForCookie(sysXUser);
        CookieUtils.setCookie(response, Constant.USER_INFO_COOKIE_KEY, JSONObject.toJSONString(vo), Constant.USER_INFO_COOKIE_EXPIRE);
    }
}
