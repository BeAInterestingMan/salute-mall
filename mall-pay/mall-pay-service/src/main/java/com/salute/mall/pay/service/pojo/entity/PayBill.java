package com.salute.mall.pay.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("pay_bill")
@ApiModel(value = "支付账单表")
public class PayBill implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "支付单号",name = "payBillCode")
    @TableField("pay_bill_code")
    private String payBillCode;

    @ApiModelProperty(value = "主单号",name = "mainOrderCode")
    @TableField("main_order_code")
    private String mainOrderCode;

    @ApiModelProperty(value = "子单号",name = "subOrderCode")
    @TableField("sub_order_code")
    private String subOrderCode;

    @ApiModelProperty(value = "支付场景",name = "paySence")
    @TableField("pay_sence")
    private String paySence;

    @ApiModelProperty(value = "支付时间",name = "payTime")
    @TableField("pay_time")
    private Date payTime;

    @ApiModelProperty(value = "支付状态",name = "payStatus")
    @TableField("pay_status")
    private String payStatus;

    @ApiModelProperty(value = "应付金额",name = "payableAmount")
    @TableField("payable_amount")
    private BigDecimal payableAmount;

    @ApiModelProperty(value = "实付金额",name = "paymentAmount")
    @TableField("payment_amount")
    private BigDecimal paymentAmount;

    @ApiModelProperty(value = "支付方式 支付宝/微信/银行卡",name = "payType")
    @TableField("pay_type")
    private String payType;

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

    @ApiModelProperty(value = "第三方支付流水号",name = "outPayCode")
    @TableField("out_pay_code")
    private String outPayCode;

    @ApiModelProperty(value = "备注",name = "remark")
    @TableField("remark")
    private String remark;

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
