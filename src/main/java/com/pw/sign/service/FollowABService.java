package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignFollow;
import com.pw.sign.entity.SignSign;
import com.pw.sign.vo.IdeaSignVoRequest;

import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月17日
 */
public interface FollowABService extends IService<SignFollow> {
    /**
     * 获取粉丝
     * @param userId
     * @return
     */
    List<SignFollow> getBList(String a);

    /**
     * 获取关注
     * @param userId
     * @return
     */
    List<SignFollow> getAList(String fan);

    int eye(String a, String id);
}
