package com.salute.mall.member.service.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("member")
@ApiModel(value = "会员表")
public class MemberBaseDTO implements Serializable {


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

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    @TableField("created_time")
    private Date createdTime;
}
