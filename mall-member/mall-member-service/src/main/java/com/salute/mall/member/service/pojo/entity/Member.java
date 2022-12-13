package com.salute.mall.member.service.pojo.entity;

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
@TableName("member")
@ApiModel(value = "会员表")
public class Member implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "会员编号",name = "memberCode")
    @TableField("member_code")
    private String memberCode;

    @ApiModelProperty(value = "会员等级编号",name = "gradeCode")
    @TableField("grade_code")
    private String gradeCode;

    @ApiModelProperty(value = "会员名称",name = "memberName")
    @TableField("member_name")
    private String memberName;

    @ApiModelProperty(value = "昵称",name = "nickName")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "性别",name = "sex")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "出生日期",name = "birthday")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "手机号",name = "mobile")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "积分",name = "point")
    @TableField("point")
    private Integer point;

    @ApiModelProperty(value = "头像",name = "avatar")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "状态",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "最后一次登录时间",name = "lastLoginDate")
    @TableField("last_login_date")
    private Date lastLoginDate;

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
