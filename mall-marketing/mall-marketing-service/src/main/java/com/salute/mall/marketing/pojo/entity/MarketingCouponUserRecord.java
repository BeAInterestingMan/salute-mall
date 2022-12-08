package com.salute.mall.marketing.pojo.entity;

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
@TableName("marketing_coupon_user_record")
@ApiModel(value = "优惠券领用记录表")
public class MarketingCouponUserRecord implements Serializable {

    @TableId
    @ApiModelProperty(value = "",name = "id")
    private Integer id;

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @TableField("coupon_activity_code")
    private String couponActivityCode;

    @ApiModelProperty(value = "优惠券实例标号",name = "couponCode")
    @TableField("coupon_code")
    private String couponCode;

    @ApiModelProperty(value = "已使用  未使用  已撤销 已过期",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "使用时间",name = "usingTime")
    @TableField("using_time")
    private Date usingTime;

    @ApiModelProperty(value = "关联业务单号",name = "relationBizCode")
    @TableField("relation_biz_code")
    private String relationBizCode;

    @ApiModelProperty(value = "优惠券生效开始时间",name = "starttime")
    @TableField("startTime")
    private Date starttime;

    @ApiModelProperty(value = "优惠券生效结束时间",name = "endtime")
    @TableField("endTime")
    private Date endtime;

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
