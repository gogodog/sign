package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignSign;
import com.pw.sign.enums.Anonymous;
import com.pw.sign.mapper.SignSignMapper;
import com.pw.sign.service.SignService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.IdeaSignVoRequest;
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
 * @date 2021年8月17日
 */
@Service
@Slf4j
public class SignServiceImpl extends ServiceImpl<SignSignMapper, SignSign> implements SignService {

    @Resource
    private SignSignMapper signMapper;
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
}
