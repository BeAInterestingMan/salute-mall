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
@TableName("menu")
@ApiModel(value = "菜单表")
public class Menu implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "菜单编号",name = "menuCode")
    @TableField("menu_code")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称",name = "menuName")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "上级菜单编号",name = "parentCode")
    @TableField("parent_code")
    private String parentCode;

    @ApiModelProperty(value = "菜单url",name = "path")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "权限",name = "permission")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "图标",name = "icon")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "层级",name = "level")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "类型  按钮   菜单",name = "type")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "排序",name = "sort")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "乐观锁",name = "version")
    @TableField("version")
    private Integer version;

    @ApiModelProperty(value = "创建人",name = "creator")
    @TableField("creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    @TableField("creator_code")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty(value = "更新人",name = "modifier")
    @TableField("modifier")
    private String modifier;

    @ApiModelProperty(value = "更新人编号",name = "modifierCode")
    @TableField("modifier_code")
    private String modifierCode;

    @ApiModelProperty(value = "更新时间",name = "modifiedTime")
    @TableField("modified_time")
    private Date modifiedTime;


}
