package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.utils.DataResult;
import com.pw.sign.entity.SysUser;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.IdeaVoRequest;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@RestController
@RequestMapping("/api/idea")
@Slf4j
public class IdeaController {

    @Resource
    IdeaService ideaService;
    @Resource
    XUserService xuserService;

    @PostMapping("/save")
    public DataResult save(@Valid IdeaVoRequest vo) {
        int i = ideaService.newSave(vo);
        log.info(JSONObject.toJSONString(vo));
        return DataResult.success();
    }
}
