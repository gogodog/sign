package com.pw.sign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.sign.entity.SignHotCode;
import com.pw.sign.entity.SignSign;
import com.pw.sign.mapper.SignHotCodeMapper;
import com.pw.sign.service.HotCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class HotCodeServiceImpl extends ServiceImpl<SignHotCodeMapper, SignHotCode> implements HotCodeService {

    @Resource
    private SignHotCodeMapper signHotCodeMapper;

    @Override
    @Transactional
    public int overwrite(String key, String type, String info) {
        SignHotCode oldSignHotCode = getOneByKeyType(key, type);
        if (oldSignHotCode != null) {
            delByKeyType(key, type);
        }
        SignHotCode signHotCode = new SignHotCode();
        signHotCode.setCkey(key);
        signHotCode.setInfo(info);
        signHotCode.setType(type);
        return this.signHotCodeMapper.insert(signHotCode);
    }

    private int delByKeyType(String key, String type) {
        LambdaQueryWrapper<SignHotCode> delWrapper = Wrappers.lambdaQuery();
        delWrapper.eq(SignHotCode::getCkey, key).eq(SignHotCode::getType, type);
        return this.signHotCodeMapper.delete(delWrapper);
    }

    @Override
    public SignHotCode getOneByKeyType(String phone, String type) {
        LambdaQueryWrapper<SignHotCode> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SignHotCode::getCkey, phone).eq(SignHotCode::getType, type).orderByDesc(SignHotCode::getCreateTime);
        List<SignHotCode> result = this.signHotCodeMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }
}
