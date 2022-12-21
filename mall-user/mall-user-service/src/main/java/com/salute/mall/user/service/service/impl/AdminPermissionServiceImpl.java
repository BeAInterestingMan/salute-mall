package com.salute.mall.user.service.service.impl;

import com.salute.mall.user.service.pojo.DO.UserPermissionDO;
import com.salute.mall.user.service.pojo.dto.UserPermissionDTO;
import com.salute.mall.user.service.repository.MenuRepository;
import com.salute.mall.user.service.service.AdminPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public UserPermissionDTO getUserPermission(String userCode) {
        List<UserPermissionDO> userPermission = menuRepository.getUserPermission(userCode);
        if(CollectionUtils.isEmpty(userPermission)){
            return null;
        }
        UserPermissionDO permissionDO = userPermission.get(0);
        List<String> permissionList = userPermission.stream().map(UserPermissionDO::getPermission).distinct().collect(Collectors.toList());
        return UserPermissionDTO.builder()
                .userCode(permissionDO.getUserCode())
                .userName(permissionDO.getUserName())
                .permissionList(permissionList).build();
    }
}
