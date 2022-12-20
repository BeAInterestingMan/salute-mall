package com.salute.mall.user.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.user.service.mapper.AdminUserMapper;
import com.salute.mall.user.service.pojo.entity.AdminUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdminUserRepository {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * @Description 更具用户编号查询用户信息
     * @author liuhu
     * @param userCode
     * @date 2022/12/20 16:40
     * @return com.salute.mall.user.service.pojo.entity.AdminUser
     */
    public AdminUser getUserInfoByUserCode(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"用户编号不能为空");
        LambdaQueryWrapper<AdminUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminUser::getUserCode,userCode);
        return adminUserMapper.selectOne(queryWrapper);
    }
}
