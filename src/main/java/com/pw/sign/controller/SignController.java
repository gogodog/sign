package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.exception.BusinessException;
import com.pw.sign.common.utils.DataResult;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.SignService;
import com.pw.sign.vo.IdeaSignVoRequest;
import com.pw.sign.vo.IdeaVoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@RestController
@RequestMapping("/api/sign")
@Slf4j
public class SignController {

    @Autowired
    SignService signService;

    @PostMapping("/idea")
    public DataResult save(@Valid IdeaSignVoRequest vo) {
        int i = signService.sign(vo);
        log.info(JSONObject.toJSONString(vo));
        return DataResult.success();
    }
}
