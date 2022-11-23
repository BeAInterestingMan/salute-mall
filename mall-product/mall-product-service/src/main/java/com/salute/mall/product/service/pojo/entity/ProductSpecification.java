package com.salute.mall.product.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("product_specification")
@ApiModel(value = "规格表")
public class ProductSpecification implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "spu编号",name = "spuCode")
    @TableField("spu_code")
    private String spuCode;

    @ApiModelProperty(value = "规格项编号",name = "specificationCode")
    @TableField("specification_code")
    private String specificationCode;

    @ApiModelProperty(value = "规格项名称",name = "specificationName")
    @TableField("specification_name")
    private String specificationName;

    @ApiModelProperty(value = "状态 ENABLE-启用  DISABLE-禁用",name = "status")
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
