package com.salute.mall.pay.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
@TableName("refund_bill")
@ApiModel(value = "退款账单表")
public class RefundBill implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "退款编号",name = "refundCode")
    @TableField("refund_code")
    private String refundCode;

    @ApiModelProperty(value = "退款场景",name = "refundSence")
    @TableField("refund_sence")
    private String refundSence;

    @ApiModelProperty(value = "退款类型",name = "refundType")
    @TableField("refund_type")
    private String refundType;

    @ApiModelProperty(value = "退款时间",name = "refundTime")
    @TableField("refund_time")
    private Date refundTime;

    @ApiModelProperty(value = "退款状态",name = "refundStatus")
    @TableField("refund_status")
    private String refundStatus;

    @ApiModelProperty(value = "应退金额",name = "returnAmount")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    @ApiModelProperty(value = "实退金额",name = "refundAmount")
    @TableField("refund_amount")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "主单号",name = "mainOrderCode")
    @TableField("main_order_code")
    private String mainOrderCode;

    @ApiModelProperty(value = "子单号",name = "subOrderCode")
    @TableField("sub_order_code")
    private String subOrderCode;

    @ApiModelProperty(value = "付款人",name = "payUserCode")
    @TableField("pay_user_code")
    private String payUserCode;

    @ApiModelProperty(value = "收款人",name = "receiveUserCode")
    @TableField("receive_user_code")
    private String receiveUserCode;

    @ApiModelProperty(value = "付款人账号",name = "payAccountCode")
    @TableField("pay_account_code")
    private String payAccountCode;

    @ApiModelProperty(value = "收款人账号",name = "receiveAccountCode")
    @TableField("receive_account_code")
    private String receiveAccountCode;

    @ApiModelProperty(value = "支付账单编号",name = "payBillCode")
    @TableField("pay_bill_code")
    private String payBillCode;

    @ApiModelProperty(value = "第三方支付编号",name = "outPayCode")
    @TableField("out_pay_code")
    private String outPayCode;

    @ApiModelProperty(value = "第三方退款编号",name = "outRefundCode")
    @TableField("out_refund_code")
    private String outRefundCode;

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
