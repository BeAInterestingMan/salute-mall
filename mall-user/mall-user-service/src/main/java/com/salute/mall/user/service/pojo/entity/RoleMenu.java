package com.salute.mall.user.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
@TableName("role_menu")
@ApiModel(value = "角色菜单表")
public class RoleMenu implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "角色编号",name = "roleCode")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "菜单编号",name = "menuCode")
    @TableField("menu_code")
    private String menuCode;


}
