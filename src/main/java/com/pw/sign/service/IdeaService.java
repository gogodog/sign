package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.Idea;
import com.pw.sign.vo.IdeaVoRequest;
import com.pw.sign.vo.SearchRequest;
import com.pw.sign.vo.UserVo;

import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月16日
 */
public interface IdeaService extends IService<Idea> {

    int newSave(IdeaVoRequest vo, UserVo user);

    List<Idea> getList();

    List<Idea> searchPage(SearchRequest searchRequest);
}
