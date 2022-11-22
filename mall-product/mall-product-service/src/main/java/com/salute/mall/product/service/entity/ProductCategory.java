package com.salute.mall.product.service.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "商品分类表")
@TableName("product_category")
@Data
public class ProductCategory implements Serializable{

    @ApiModelProperty(name = "id")
    @TableId
    private Long id ;

    @ApiModelProperty(name = "租户号")
    private String tenantId ;

    @ApiModelProperty(name = "分类级别")
    private String categoryLevel;

    @ApiModelProperty(name = "分类编号")
    private String categoryCode ;

    @ApiModelProperty(name = "分类名称")
    private String categoryName ;

    @ApiModelProperty(name = "分类描述")
    private String categoryDesc ;

    @ApiModelProperty(name = "分类图标")
    private String icon ;

    @ApiModelProperty(name = "状态")
    private String status ;

    @ApiModelProperty(name = "删除标志",notes = "YES-已删除 NO-正常")
    private String deleteFlag;

    @ApiModelProperty(name = "排序")
    private Integer sort ;

    @ApiModelProperty(name = "乐观锁")
    private String version ;

    @ApiModelProperty(name = "创建人")
    private String creator ;

    @ApiModelProperty(name = "创建人编号")
    private String creatorCode ;

    @ApiModelProperty(name = "创建时间")
    private Date createdTime ;

    @ApiModelProperty(name = "更新人")
    private String modifier ;

    @ApiModelProperty(name = "更新人编号")
    private String modifierCode ;

    @ApiModelProperty(name = "更新时间")
    private Date modifiedTime ;

}