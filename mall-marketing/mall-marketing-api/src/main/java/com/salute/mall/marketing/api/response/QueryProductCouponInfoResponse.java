package com.salute.mall.marketing.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("商品维度优惠信息")
public class QueryProductCouponInfoResponse implements Serializable {

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    private String couponActivityCode;

    @ApiModelProperty(value = "优惠券活动名称",name = "couponActivityName")
    private String couponActivityName;

    @ApiModelProperty(value = "开始时间",name = "startTime")
    private Date startTime;

    @ApiModelProperty(value = "结束时间",name = "endTime")
    private Date endTime;

    @ApiModelProperty(value = "优惠券面值",name = "couponAmount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "1.主动领取 2.订单满赠 3.新用户发放 4.指定用户发放",name = "sendType")
    private String sendType;

    @ApiModelProperty(value = "1.指定商品可用 2.指定分类可用 3.订单满减",name = "useType")
    private String useType;

    @ApiModelProperty(value = "使用类型  满减券  无门槛",name = "couponType")
    private String couponType;


}
