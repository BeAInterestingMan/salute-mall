package com.salute.mall.order.service.pojo.entity;

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
@TableName("delivery_order")
@ApiModel(value = "发货单")
public class DeliveryOrder implements Serializable {

    @TableId
    @ApiModelProperty(value = "",name = "id")
    private Integer id;

    @ApiModelProperty(value = "订单编号",name = "saleOrderCode")
    @TableField("sale_order_code")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货单号",name = "deliveryOrderCode")
    @TableField("delivery_order_code")
    private String deliveryOrderCode;

    @ApiModelProperty(value = "店铺名称",name = "shopCode")
    @TableField("shop_code")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "发订单状态  待发货 待收货 已完成 已取消",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "支付方式  线上  线下",name = "payMode")
    @TableField("pay_mode")
    private String payMode;

    @ApiModelProperty(value = "支付类型  微信 支付宝  现金 银行卡",name = "payType")
    @TableField("pay_type")
    private String payType;

    @ApiModelProperty(value = "支付状态 待支付 支付成功 支付失败",name = "payStatus")
    @TableField("pay_status")
    private String payStatus;

    @ApiModelProperty(value = "支付时间",name = "payTime")
    @TableField("pay_time")
    private Date payTime;

    @ApiModelProperty(value = "支付编号",name = "payCode")
    @TableField("pay_code")
    private String payCode;

    @ApiModelProperty(value = "成本价总额",name = "totalCostAmount")
    @TableField("total_cost_amount")
    private BigDecimal totalCostAmount;

    @ApiModelProperty(value = "未参与营销的总额",name = "totalSaleAmount")
    @TableField("total_sale_amount")
    private BigDecimal totalSaleAmount;

    @ApiModelProperty(value = "应付金额",name = "totalPayableAmount")
    @TableField("total_payable_amount")
    private BigDecimal totalPayableAmount;

    @ApiModelProperty(value = "使用优惠券金额",name = "totalCouponAmount")
    @TableField("total_coupon_amount")
    private BigDecimal totalCouponAmount;

    @ApiModelProperty(value = "实付金额",name = "totalPaymentAmount")
    @TableField("total_payment_amount")
    private String totalPaymentAmount;

    @ApiModelProperty(value = "实际发货应付金额",name = "totalDeliveryPaymentAmount")
    @TableField("total_delivery_payment_amount")
    private BigDecimal totalDeliveryPaymentAmount;

    @ApiModelProperty(value = "实际发货应付金额",name = "totalDeliveryPayableAmount")
    @TableField("total_delivery_payable_amount")
    private BigDecimal totalDeliveryPayableAmount;

    @ApiModelProperty(value = "实际发货使用优惠券金额",name = "totalDeliveryCouponAmount")
    @TableField("total_delivery_coupon_amount")
    private BigDecimal totalDeliveryCouponAmount;

    @ApiModelProperty(value = "成本价总额",name = "totalDeliveryCostAmount")
    @TableField("total_delivery_cost_amount")
    private BigDecimal totalDeliveryCostAmount;

    @ApiModelProperty(value = "未参与营销的总额",name = "totalDeliverySaleAmount")
    @TableField("total_delivery_sale_amount")
    private BigDecimal totalDeliverySaleAmount;

    @ApiModelProperty(value = "订单备注",name = "orderRemark")
    @TableField("order_remark")
    private String orderRemark;

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
