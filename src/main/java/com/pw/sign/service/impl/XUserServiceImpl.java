package com.pw.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.common.exception.BusinessException;
import com.pw.sign.common.exception.code.BaseResponseCode;
import com.pw.sign.common.utils.Constant;
import com.pw.sign.common.utils.CookieUtils;
import com.pw.sign.entity.SignSign;
import com.pw.sign.entity.SysUser;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.mapper.SysXUserMapper;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@Service
@Slf4j
public class XUserServiceImpl extends ServiceImpl<SysXUserMapper, SysXUser> implements XUserService {

    @Resource
    private SysXUserMapper sysXUserMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;


    @Override
    public SysXUser register(String tel) {
        SysXUser oldUser = this.getByPhone(tel);
        if (oldUser != null) {
            throw new BusinessException(BaseResponseCode.EXISTED_USER);
        }
        SysXUser entity = newXuserByTel(tel);
        new SysXUser();
        entity.setCreateId("system");
        entity.setId(UUID.randomUUID().toString());
        entity.setPhone(tel);
        entity.setUsername("user" + tel);
        int i = sysXUserMapper.insert(entity);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.NEW_USER_ERRO);
        }
        return this.sysXUserMapper.selectById(entity.getId());
    }

    @Override
    public SysXUser getByPhone(String tel) {
        LambdaQueryWrapper<SysXUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysXUser::getPhone, tel);
        return this.sysXUserMapper.selectOne(queryWrapper);
    }

    @Override
    public SysXUser getXUserFromCookie() {
        UserVo vo = this.getUserFromCookie();
        if (vo == null) {
            throw new BusinessException(BaseResponseCode.NO_LOGIN);
        }
        SysXUser user = this.sysXUserMapper.selectById(vo.getId());
        if (user == null) {
            throw new BusinessException(BaseResponseCode.NO_USER);
        }
        return user;
    }

    @Override
    public UserVo getUserFromCookie() {
        try {
            String val = CookieUtils.getCookie(request, Constant.USER_INFO_COOKIE_KEY);
            if (StringUtils.isEmpty(val)) {
                return null;
            }
            return JSONObject.parseObject(val, UserVo.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("get user info from cookie error {}", e);
            throw new BusinessException(BaseResponseCode.NO_LOGIN);
        }
    }

    @Override
    public void logout() {
        try {
            CookieUtils.deleteCookie(response, Constant.USER_INFO_COOKIE_KEY);
        } catch (UnsupportedEncodingException e) {
            log.error("注销异常{}", e);
            throw new BusinessException(BaseResponseCode.SYSTEM_BUSY);
        }
    }

    @Override
    public UserVo findByUserId(String id) {
        return new UserVo(this.sysXUserMapper.selectById(id));
    }

    private SysXUser newXuserByTel(String tel) {
        SysXUser entity = new SysXUser();
        entity.setCreateId("system");
        entity.setId(UUID.randomUUID().toString());
        entity.setSalt(UUID.randomUUID().toString());
        entity.setPhone(tel);
        entity.setUsername("user" + tel);
        entity.setNickName("USER" + tel);
        entity.setHeadImg("https://img2.baidu.com/it/u=3890643864,1729804458&fm=26&fmt=auto&gp=0.jpg");
        return entity;
    }
}
