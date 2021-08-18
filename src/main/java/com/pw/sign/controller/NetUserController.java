package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.exception.code.BaseResponseCode;
import com.pw.sign.common.utils.Constant;
import com.pw.sign.common.utils.CookieUtils;
import com.pw.sign.common.utils.DataResult;
import com.pw.sign.common.utils.Secret;
import com.pw.sign.controller.helper.NetUserControllerHelper;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.service.SmsService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.RegisterVo;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/nuser")
@Slf4j
public class NetUserController {

    @Resource
    SmsService smsService;
    @Resource
    XUserService  xUserService;

    @PostMapping("/register")
    public DataResult register(@Valid RegisterVo vo, HttpServletResponse response) throws UnsupportedEncodingException {
        boolean yes =  this.smsService.checkAuthCodeByAliYun(vo.getTel(), vo.getVerityCode());
        if(yes){
            SysXUser user = xUserService.register(vo.getTel());
            NetUserControllerHelper.secretAndSetCookie(user, response);
            return DataResult.success(new UserVo(user));
        }
        return DataResult.getResult(BaseResponseCode.VERITY_CODE_NOT_VALID_EXCEPTION);
    }

    @PostMapping("/login")
    public DataResult login(@Valid RegisterVo vo, HttpServletResponse response) throws UnsupportedEncodingException {
        boolean yes =  this.smsService.checkAuthCodeByAliYun(vo.getTel(), vo.getVerityCode());
        if(yes){
            SysXUser user = this.xUserService.getByPhone(vo.getTel());
            if(user == null){
                user = xUserService.register(vo.getTel());
            }
            NetUserControllerHelper.secretAndSetCookie(user, response);
            return DataResult.success(new UserVo(user));
        }
        return DataResult.getResult(BaseResponseCode.VERITY_CODE_NOT_VALID_EXCEPTION);
    }

    @PostMapping("/getCode")
    public DataResult getCode(String tel, String area) {
        boolean isSend = this.smsService.sendAuthCodeByAliYun(tel);
        if(isSend){
            return DataResult.success();
        }
        return DataResult.getResult(BaseResponseCode.SYSTEM_BUSY);
    }
}
