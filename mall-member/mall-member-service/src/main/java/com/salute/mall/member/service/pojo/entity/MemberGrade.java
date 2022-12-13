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
@TableName("member_grade")
@ApiModel(value = "会员等级表")
public class MemberGrade implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "等级编码",name = "gradeCode")
    @TableField("grade_code")
    private String gradeCode;

    @ApiModelProperty(value = "等级名称",name = "gradeName")
    @TableField("grade_name")
    private String gradeName;

    @ApiModelProperty(value = "所需经验值",name = "experienceValue")
    @TableField("experience_value")
    private Integer experienceValue;

    @ApiModelProperty(value = "等级图片",name = "gradeImage")
    @TableField("grade_image")
    private String gradeImage;

    @ApiModelProperty(value = "排序",name = "sort")
    @TableField("sort")
    private Integer sort;

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
