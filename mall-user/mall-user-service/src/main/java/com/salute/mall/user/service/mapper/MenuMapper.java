package com.salute.mall.user.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salute.mall.user.service.pojo.DO.UserPermissionDO;
import com.salute.mall.user.service.pojo.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @Description 查询用户权限
     * @author liuhu
     * @param userCode
     * @date 2022/12/21 16:50
     * @return com.salute.mall.user.service.pojo.DO.UserPermissionDO
     */
    List<UserPermissionDO> getUserPermission(@Param("userCode") String userCode);
}
