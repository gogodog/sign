package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SysUser;
import com.pw.sign.mapper.IdeaMapper;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.IdeaVoRequest;
import com.pw.sign.vo.SearchRequest;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月16日
 */
@Service
@Slf4j
public class IdeaServiceImpl extends ServiceImpl<IdeaMapper, Idea> implements IdeaService {

    @Resource
    private IdeaMapper ideaMapper;


    @Override
    public int newSave(IdeaVoRequest vo, UserVo user) {
        Idea idea = new Idea();
        idea.setIdea(JSONObject.toJSONString(vo));
        idea.setCreator(user.getId());
        idea.setCreatorName(user.getNickName());
        idea.setUpdater(user.getId());
        return this.ideaMapper.insert(idea);
    }

    @Override
    public List<Idea> getList() {
        LambdaQueryWrapper<Idea> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(Idea::getId);
        return this.ideaMapper.selectList(queryWrapper);
    }

    @Override
    public List<Idea> searchPage(SearchRequest searchRequest) {
        Page page = new Page(searchRequest.getPage(), searchRequest.getLimit());
        LambdaQueryWrapper<Idea> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(Idea::getIdea, searchRequest.getKey());
        queryWrapper.orderByDesc(Idea::getCreateTime);
        IPage<Idea> iPage = this.ideaMapper.selectPage(page, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<Idea> getListByAuthorId(String id) {
        LambdaQueryWrapper<Idea> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Idea::getCreator, id);
        queryWrapper.orderByDesc(Idea::getCreateTime);
        return this.ideaMapper.selectList(queryWrapper);
    }
}
