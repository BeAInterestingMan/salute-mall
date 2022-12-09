package com.salute.mall.marketing.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;


@Data
@TableName("marketing_coupon_send_rule_detail")
@ApiModel(value = "营销-优惠券发放规则明细表")
public class MarketingCouponSendRuleDetail implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;

    @ApiModelProperty(value = "指定用户",name = "specificType")
    @TableField("specific_type")
    private String specificType;

    @ApiModelProperty(value = "特定编号 1-指定用户 则为用户编号",name = "specificCode")
    @TableField("specific_code")
    private String specificCode;

    @ApiModelProperty(value = "特定名称",name = "specificName")
    @TableField("specific_name")
    private String specificName;

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
