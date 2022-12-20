package com.salute.mall.user.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("admin_user")
@ApiModel(value = "管理后台用户表")
public class AdminUser implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "用户名称",name = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户编码",name = "userCode")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty(value = "昵称",name = "nickName")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "手机号",name = "mobile")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "邮件",name = "email")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "头像",name = "avatar")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "性别",name = "sex")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "描述",name = "description")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "最后一次登录时间",name = "lastLoginTime")
    @TableField("last_login_time")
    private Date lastLoginTime;

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
