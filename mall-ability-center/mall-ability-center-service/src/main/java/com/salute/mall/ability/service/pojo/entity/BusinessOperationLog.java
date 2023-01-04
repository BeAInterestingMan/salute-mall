package com.salute.mall.ability.service.pojo.entity;

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
@TableName("business_operation_log")
@ApiModel(value = "业务操作日志表")
public class BusinessOperationLog implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @TableField("biz_code")
    private String bizCode;

    @ApiModelProperty(value = "应用ID",name = "appId")
    @TableField("app_id")
    private String appId;

    @ApiModelProperty(value = "场景号",name = "scene")
    @TableField("scene")
    private String scene;

    @ApiModelProperty(value = "日志描述",name = "logDesc")
    @TableField("log_desc")
    private String logDesc;

    @ApiModelProperty(value = "操作时间",name = "operateTime")
    @TableField("operate_time")
    private Date operateTime;

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
