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
@TableName("marketing_coupon_stock")
@ApiModel(value = "优惠券库存表")
public class MarketingCouponStock implements Serializable {

    @TableId
    @ApiModelProperty(value = "",name = "id")
    private Integer id;

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

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;

    @ApiModelProperty(value = "真实库存",name = "realStock")
    @TableField("real_stock")
    private Integer realStock;

    @ApiModelProperty(value = "已发放库存",name = "sendStock")
    @TableField("send_stock")
    private Integer sendStock;

    @ApiModelProperty(value = "可用库存",name = "availableStock")
    @TableField("available_stock")
    private Integer availableStock;


}
