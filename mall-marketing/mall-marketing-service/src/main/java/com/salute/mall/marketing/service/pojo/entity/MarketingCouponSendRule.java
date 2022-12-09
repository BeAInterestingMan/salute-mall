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
@TableName("marketing_coupon_send_rule")
@ApiModel(value = "营销优惠券发放规则表")
public class MarketingCouponSendRule implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;

    @ApiModelProperty(value = "1.主动领取 2.订单满赠 3.新用户发放 4.指定用户发放5.指定用户类型发放",name = "sendType")
    @TableField("send_type")
    private String sendType;

    @ApiModelProperty(value = "单次发放数量",name = "sendQty")
    @TableField("send_qty")
    private Integer sendQty;

    @ApiModelProperty(value = "限制参与次数 -1-表示无限制",name = "participateLimitQty")
    @TableField("participate_limit_qty")
    private Integer participateLimitQty;

    @ApiModelProperty(value = "订单满赠限制金额-发放方式为订单满赠时必填",name = "orderFullAmount")
    @TableField("order_full_amount")
    private BigDecimal orderFullAmount;

    @ApiModelProperty(value = "发放场景  订单满赠为 指定分类商品满足  指定商品满足   ",name = "sendSence")
    @TableField("send_sence")
    private String sendSence;

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
