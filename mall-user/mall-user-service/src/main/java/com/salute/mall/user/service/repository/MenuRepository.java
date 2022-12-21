package com.salute.mall.user.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.user.service.mapper.MenuMapper;
import com.salute.mall.user.service.pojo.DO.UserPermissionDO;
import com.salute.mall.user.service.pojo.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MenuRepository {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * @Description 根据菜单编号获取菜单信息
     * @author liuhu
     * @param menuCodeList
     * @date 2022/12/21 16:38
     * @return com.salute.mall.user.service.pojo.entity.Menu
     */
    public Menu getUserInfoByUserCode(List<String> menuCodeList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(menuCodeList),"菜单编号不能为空");
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Menu::getMenuCode,menuCodeList);
        return menuMapper.selectOne(queryWrapper);
    }

    /**
     * @Description 获取用户权限
     * @author liuhu
     * @param userCode
     * @date 2022/12/21 16:50
     * @return com.salute.mall.user.service.pojo.DO.UserPermissionDO
     */
    public List<UserPermissionDO> getUserPermission(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"用户编号不能为空");
        return menuMapper.getUserPermission(userCode);
    }
}
