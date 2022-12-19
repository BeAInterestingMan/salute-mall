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
@TableName("address")
@ApiModel(value = "地址表")
public class Address implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "地址编号",name = "addressCode")
    @TableField("address_code")
    private String addressCode;

    @ApiModelProperty(value = "用户编号",name = "userCode")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty(value = "收货人",name = "receiver")
    @TableField("receiver")
    private String receiver;

    @ApiModelProperty(value = "省编码",name = "provinceCode")
    @TableField("province_code")
    private String provinceCode;

    @ApiModelProperty(value = "省名称",name = "provinceName")
    @TableField("province_name")
    private String provinceName;

    @ApiModelProperty(value = "区编码",name = "areaCode")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "区名称",name = "areaName")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "市编码",name = "cityCode")
    @TableField("city_code")
    private String cityCode;

    @ApiModelProperty(value = "市名称",name = "cityName")
    @TableField("city_name")
    private String cityName;

    @ApiModelProperty(value = "地址code全拼",name = "addressCodePath")
    @TableField("address_code_path")
    private String addressCodePath;

    @ApiModelProperty(value = "详细地址",name = "detailAddress")
    @TableField("detail_address")
    private String detailAddress;

    @ApiModelProperty(value = "地址全拼",name = "address")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "邮编",name = "postCode")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty(value = "手机号",name = "mobile")
    @TableField("mobile")
    private String mobile;

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
