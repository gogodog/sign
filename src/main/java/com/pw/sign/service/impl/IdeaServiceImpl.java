package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SysUser;
import com.pw.sign.mapper.IdeaMapper;
import com.pw.sign.service.IdeaService;
import com.pw.sign.vo.IdeaVoRequest;
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
    public int newSave(IdeaVoRequest vo) {
        Idea idea = new Idea();
        idea.setIdea(JSONObject.toJSONString(vo));
        return this.ideaMapper.insert(idea);
    }

    @Override
    public List<Idea> getList() {
        LambdaQueryWrapper<Idea> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(Idea::getId);
        return this.ideaMapper.selectList(queryWrapper);
    }
}
