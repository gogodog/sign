package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.aop.annotation.NeedLogin;
import com.pw.sign.common.utils.DataResult;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.service.HandlerJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@RestController
@RequestMapping("/api/job")
@Slf4j
public class JobController {

    @Resource
    HandlerJobService handlerJobService;

    @GetMapping("/toHeadImg/base64")
    @NeedLogin
    public DataResult save() {
        int i = handlerJobService.userHeadImgToBase64();
        return DataResult.success("transfer:" + i + "rows");
    }
}
