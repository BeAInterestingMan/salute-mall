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
@TableName("delivery_order_detail")
@ApiModel(value = "发货单详情表")
public class DeliveryOrderDetail implements Serializable {

    @TableId
    @ApiModelProperty(value = "",name = "id")
    private Long id;

    @ApiModelProperty(value = "订单编号",name = "saleOrderCode")
    @TableField("sale_order_code")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货单编号",name = "deliveryOrderCode")
    @TableField("delivery_order_code")
    private String deliveryOrderCode;

    @ApiModelProperty(value = "商品编码",name = "skuCode")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "商品sku名称",name = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "商品名称",name = "productName")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

    @ApiModelProperty(value = "店铺名称",name = "shopCode")
    @TableField("shop_code")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "购买数量",name = "buyQty")
    @TableField("buy_qty")
    private BigDecimal buyQty;

    @ApiModelProperty(value = "赠品   正常购买商品",name = "orderSkuType")
    @TableField("order_sku_type")
    private String orderSkuType;

    @ApiModelProperty(value = "备注",name = "remark")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "发货数量",name = "deliverySkuQty")
    @TableField("delivery_sku_qty")
    private Integer deliverySkuQty;

    @ApiModelProperty(value = "成本价总额",name = "costPrice")
    @TableField("cost_price")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "销售金额（未参与营销）",name = "salePrice")
    @TableField("sale_price")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "成交价(扣除各种优惠后的)",name = "transactionPrice")
    @TableField("transaction_price")
    private BigDecimal transactionPrice;

    @ApiModelProperty(value = "市场价",name = "marketingPrice")
    @TableField("marketing_price")
    private BigDecimal marketingPrice;

    @ApiModelProperty(value = "应付金额",name = "payableAmount")
    @TableField("payable_amount")
    private BigDecimal payableAmount;

    @ApiModelProperty(value = "实付金额",name = "paymentAmount")
    @TableField("payment_amount")
    private BigDecimal paymentAmount;

    @ApiModelProperty(value = "使用优惠券金额",name = "couponAmount")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "发货应付金额",name = "deliveryPayableAmount")
    @TableField("delivery_payable_amount")
    private BigDecimal deliveryPayableAmount;

    @ApiModelProperty(value = "发货实付金额",name = "deliveryPaymentAmount")
    @TableField("delivery_payment_amount")
    private BigDecimal deliveryPaymentAmount;

    @ApiModelProperty(value = "发货使用优惠券金额",name = "deliveryCouponAmount")
    @TableField("delivery_coupon_amount")
    private BigDecimal deliveryCouponAmount;

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
