package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.Idea;
import com.pw.sign.vo.IdeaVoRequest;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月16日
 */
public interface IdeaService extends IService<Idea> {

    int newSave(IdeaVoRequest vo);
}
