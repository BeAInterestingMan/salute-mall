package com.salute.mall.marketing.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("marketing_coupon_use_rule")
@ApiModel(value = "营销优惠券使用规则表")
public class MarketingCouponUseRule implements Serializable {

    @TableId
    @ApiModelProperty(value = "",name = "id")
    private Integer id;

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;


    @ApiModelProperty(value = "1.指定商品可用 2.指定分类可用 3.订单满减",name = "useType")
    @TableField("coupon_type")
    private String couponType;

    @ApiModelProperty(value = "1.指定商品可用 2.指定分类可用 3.订单满减",name = "useType")
    @TableField("use_type")
    private String useType;

    @ApiModelProperty(value = "优惠券使用门槛金额",name = "couponFullAmount")
    @TableField("coupon_full_amount")
    private BigDecimal couponFullAmount;

    @ApiModelProperty(value = "是否与其他活动互斥",name = "mutexFlag")
    @TableField("mutex_flag")
    private String mutexFlag;

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
