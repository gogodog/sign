package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.Idea;
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
public interface SignService extends IService<SignSign> {
    int sign(IdeaSignVoRequest vo);

    List<SignSign> listByIdeaId(Integer id);

    List<Idea> getListBySign(String id);
}
