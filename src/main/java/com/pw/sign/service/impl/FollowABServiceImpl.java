package com.pw.sign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.SignFollow;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.mapper.SignFollowMapper;
import com.pw.sign.service.FollowABService;
import com.pw.sign.service.XUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月21日
 * A是关注的对象
 * B是关注者
 */
@Service
@Slf4j
public class FollowABServiceImpl extends ServiceImpl<SignFollowMapper, SignFollow> implements FollowABService {

    @Resource
    private SignFollowMapper signFollowMapper;
    @Resource
    private XUserService xUserService;

    /**
     * 获取粉丝
     * @return
     */
    @Override
    public List<SignFollow> getBList(String a) {
        LambdaQueryWrapper<SignFollow> conditions = Wrappers.lambdaQuery();
        conditions.eq(SignFollow::getAUser, a);
        return this.signFollowMapper.selectList(conditions);
    }

    /**
     * 获取关注
     * @return
     */
    @Override
    public List<SignFollow> getAList(String fan) {
        LambdaQueryWrapper<SignFollow> conditions = Wrappers.lambdaQuery();
        conditions.eq(SignFollow::getBUser, fan);
        return this.signFollowMapper.selectList(conditions);
    }

    @Override
    public int eye(String a, String b) {
        LambdaQueryWrapper<SignFollow> conditions = Wrappers.lambdaQuery();
        conditions.eq(SignFollow::getAUser, a).eq(SignFollow::getBUser, b);
        List<SignFollow> follows = this.signFollowMapper.selectList(conditions);
        if(CollectionUtils.isEmpty(follows)){
            SysXUser aUser = this.xUserService.getById(a);
            SysXUser bUser = this.xUserService.getById(a);
            if(aUser == null || bUser == null){
                return -1;
            }
            return insert(aUser, bUser);
        }else{
            return delete(a, b);
        }
    }

    private int delete(String a, String b) {
        LambdaQueryWrapper<SignFollow> conditions = Wrappers.lambdaQuery();
        conditions.eq(SignFollow::getAUser, a).eq(SignFollow::getBUser, b);
        this.signFollowMapper.delete(conditions);
        return 0;
    }

    private int insert(SysXUser aUser, SysXUser bUser) {
        SignFollow signFollow = new SignFollow();
        signFollow.setAUser(aUser.getId());
        signFollow.setAUserHead(aUser.getHeadImg());
        signFollow.setAUserName(aUser.getNickName());
        signFollow.setBUser(bUser.getId());
        signFollow.setBUserHead(bUser.getHeadImg());
        signFollow.setBUserName(bUser.getNickName());
        return this.signFollowMapper.insert(signFollow);
    }
}
