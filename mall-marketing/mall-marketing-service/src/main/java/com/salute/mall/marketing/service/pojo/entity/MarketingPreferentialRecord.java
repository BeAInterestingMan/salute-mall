package com.salute.mall.marketing.service.pojo.entity;

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
@TableName("marketing_preferential_record")
@ApiModel(value = "营销优惠分摊记录表")
public class MarketingPreferentialRecord implements Serializable {

    @TableId
    @ApiModelProperty(value = "主键",name = "id")
    private Long id;

    @ApiModelProperty(value = "优惠记录唯一编号",name = "preferentialRecordCode")
    @TableField("preferential_record_code")
    private String preferentialRecordCode;

    @ApiModelProperty(value = "优惠活动类型 COUPON 优惠券 ACTIVITY 活动",name = "preferentialType")
    @TableField("preferential_type")
    private String preferentialType;

    @ApiModelProperty(value = "优惠券指优惠券实例编号，活动指活动编号",name = "preferentialCode")
    @TableField("preferential_code")
    private String preferentialCode;

    @ApiModelProperty(value = "活动名称",name = "preferentialName")
    @TableField("preferential_name")
    private String preferentialName;

    @ApiModelProperty(value = "订单编号",name = "saleOrderCode")
    @TableField("sale_order_code")
    private String saleOrderCode;

    @ApiModelProperty(value = "商品skuCode",name = "skuCode")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "商品名称",name = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "购买数量",name = "buyQty")
    @TableField("buy_qty")
    private Integer buyQty;

    @ApiModelProperty(value = "优惠状态 RUNNING-进行中 ENABLE-生效 DISABLE-失效",name = "preferentialStatus")
    @TableField("preferential_status")
    private String preferentialStatus;

    @ApiModelProperty(value = "商品价格",name = "salePrice")
    @TableField("sale_price")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品原价总额",name = "totalOriginAmount")
    @TableField("total_origin_amount")
    private BigDecimal totalOriginAmount;

    @ApiModelProperty(value = "商品分摊的优惠金额",name = "totalPreferentialAmount")
    @TableField("total_preferential_amount")
    private BigDecimal totalPreferentialAmount;

    @ApiModelProperty(value = "优惠后金额",name = "totalPreferentialAfterAmount")
    @TableField("total_preferential_after_amount")
    private BigDecimal totalPreferentialAfterAmount;

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
