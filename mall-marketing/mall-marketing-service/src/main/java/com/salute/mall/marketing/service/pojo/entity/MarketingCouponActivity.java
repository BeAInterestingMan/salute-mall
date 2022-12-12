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
@TableName("marketing_coupon_activity")
@ApiModel(value = "优惠券活动表")
public class MarketingCouponActivity implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;

    @ApiModelProperty(value = "优惠券活动名称",name = "couponActivityName")
    @TableField("coupon_activity_name")
    private String couponActivityName;

    @ApiModelProperty(value = "门店编号",name = "shopCode")
    @TableField("shop_code")
    private String shopCode;

    @ApiModelProperty(value = "门店名称",name = "shopName")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "开始时间",name = "startTime")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "结束时间",name = "endTime")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "优惠券面值",name = "couponAmount")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "1.主动领取 2.订单满赠 3.新用户发放 4.指定用户发放",name = "sendType")
    @TableField("send_type")
    private String sendType;

    @ApiModelProperty(value = "1.指定商品可用 2.指定分类可用 3.订单满减",name = "useType")
    @TableField("use_type")
    private String useType;

    @ApiModelProperty(value = "使用类型  满减券  无门槛",name = "couponType")
    @TableField("coupon_type")
    private String couponType;

    @ApiModelProperty(value = "状态 ON上架 DOWN下架",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "删除标志;YES-已删除 NO-正常",name = "deleteFlag")
    @TableField("delete_flag")
    private String deleteFlag;

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
