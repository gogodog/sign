package com.pw.sign.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pw.sign.common.utils.ImgUtils;
import com.pw.sign.entity.SysXUser;
import com.pw.sign.mapper.SysXUserMapper;
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
public class HandlerJobServiceImpl implements HandlerJobService {

    @Resource
    private SysXUserMapper sysXUserMapper;

    @Override
    public int userHeadImgToBase64() {
        LambdaQueryWrapper<SysXUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.likeRight(SysXUser::getHeadImg, "http");
        List<SysXUser> rows = this.sysXUserMapper.selectList(queryWrapper);
        int i = 0;
        for (SysXUser row : rows) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
            i += userHeadImgToBase64HandlerRow(row);
            log.info("updated {} rows", i);
//                }
//            }).start();

        }
        return i;
    }

    private int userHeadImgToBase64HandlerRow(SysXUser row) {
        try {
            String base64 = ImgUtils.getBase64FromUrl(row.getHeadImg());
            SysXUser newUser = new SysXUser();
            newUser.setHeadImg(base64);
            newUser.setId(row.getId());
            return this.sysXUserMapper.updateById(newUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }

    }
}
