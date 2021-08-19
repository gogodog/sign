package com.pw.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.vo.UserVo;

/**
 * 用户 服务类
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
public interface XUserService extends IService<SysXUser> {

    SysXUser register(String tel);

    SysXUser getByPhone(String tel);

    SysXUser getXUserFromCookie();

    UserVo getUserFromCookie();

    void logout();

    UserVo findByUserId(String id);
}