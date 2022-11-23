package com.salute.mall.product.service.pojo.entity;

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
@TableName("product_stock_transaction")
@ApiModel(value = "库存流水表")
public class ProductStockTransaction implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "操作类型",name = "operateType")
    @TableField("operate_type")
    private String operateType;

    @ApiModelProperty(value = "操作时间",name = "operateTime")
    @TableField("operate_time")
    private Date operateTime;

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @TableField("biz_code")
    private String bizCode;

    @ApiModelProperty(value = "操作前真实库存",name = "beforeRealStock")
    @TableField("before_real_stock")
    private Integer beforeRealStock;

    @ApiModelProperty(value = "操作后真实库存",name = "afterRealStock")
    @TableField("after_real_stock")
    private Integer afterRealStock;

    @ApiModelProperty(value = "操作前冻结库存",name = "beforeFreezeStock")
    @TableField("before_freeze_stock")
    private Integer beforeFreezeStock;

    @ApiModelProperty(value = "操作后冻结库存",name = "afterFreezeStock")
    @TableField("after_freeze_stock")
    private Integer afterFreezeStock;

    @ApiModelProperty(value = "操作库存数量",name = "operateStock")
    @TableField("operate_stock")
    private Integer operateStock;

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
