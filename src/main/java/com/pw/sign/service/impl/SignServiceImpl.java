package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignSign;
import com.pw.sign.enums.Anonymous;
import com.pw.sign.mapper.IdeaMapper;
import com.pw.sign.mapper.SignSignMapper;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.SignService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.IdeaSignVoRequest;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月17日
 */
@Service
@Slf4j
public class SignServiceImpl extends ServiceImpl<SignSignMapper, SignSign> implements SignService {

    @Resource
    private SignSignMapper signMapper;
    @Resource
    private IdeaService ideaService;
    @Resource
    private XUserService xuserService;

    @Override
    public int sign(IdeaSignVoRequest vo) {
        UserVo user = this.xuserService.getUserFromCookie();
        SignSign sign = new SignSign();
        sign.setAnonymous(Anonymous.getAnonymous(user));
        sign.setType(vo.getSignType());
        sign.setIdeaId(vo.getIdeaId());
        sign.setInfo(JSONObject.toJSONString(vo));
        sign.setIp(vo.getIp());
        if (user != null) {
            sign.setCreator(user.getId());
            sign.setCreatorHead(user.getHeadImg());
            sign.setCreatorName(user.getNickName());
        }
        return this.signMapper.insert(sign);
    }

    @Override
    public List<SignSign> listByIdeaId(Integer id) {
        LambdaQueryWrapper<SignSign> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SignSign::getIdeaId, id);
        return this.signMapper.selectList(queryWrapper);
    }

    @Override
    public List<Idea> getListBySign(String id) {
        List<SignSign> signs = getSignListBySignerId(id);
        if(CollectionUtils.isEmpty(signs)){
            return new ArrayList<>();
        }
        List<Integer> ideaIds = new ArrayList<>(signs.size());
        signs.forEach(e -> {
            ideaIds.add(e.getIdeaId());
        });
        return this.ideaService.listByIds(ideaIds);
    }

    private List<SignSign> getSignListBySignerId(String id) {
        LambdaQueryWrapper<SignSign> signLambdaQueryWrapper = Wrappers.lambdaQuery();
        signLambdaQueryWrapper.eq(SignSign::getCreator, id);
        return this.signMapper.selectList(signLambdaQueryWrapper);
    }
}
