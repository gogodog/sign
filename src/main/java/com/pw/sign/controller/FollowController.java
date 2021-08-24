package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.aop.annotation.NeedLogin;
import com.pw.sign.common.exception.code.BaseResponseCode;
import com.pw.sign.common.utils.DataResult;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignFollow;
import com.pw.sign.service.FollowABService;
import com.pw.sign.service.SignService;
import com.pw.sign.vo.IdeaSignVoRequest;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 用户管理
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@RestController
@RequestMapping("/api/follow")
@Slf4j
public class FollowController {

    @Autowired
    FollowABService followABService;

    @PostMapping("/fans")
    public DataResult a(@NotEmpty String user) {
        List<SignFollow> ideas = this.followABService.getBList(user);
        return DataResult.success(ideas);
    }

    @PostMapping("/follow")
    public DataResult b(@NotEmpty String user) {
        List<SignFollow> ideas = this.followABService.getAList(user);
        return DataResult.success(ideas);
    }

    @PostMapping("/eye")
    public DataResult b(@NotEmpty String a, UserVo b) {
        switch (this.followABService.eye(a, b.getId())) {
            case 0: return DataResult.success(BaseResponseCode.FOLLOW_CANCEL_SUCCESS.getCode());
            case 1: return DataResult.success(BaseResponseCode.FOLLOW_ADD_SUCCESS.getCode());
        }
        return DataResult.getResult(BaseResponseCode.SYSTEM_BUSY);
    }
}
