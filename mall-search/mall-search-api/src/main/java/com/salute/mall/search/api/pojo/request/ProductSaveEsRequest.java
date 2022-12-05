package com.salute.mall.search.api.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@FieldNameConstants
public class ProductSaveEsRequest implements Serializable {

    @ApiModelProperty(value = "商品名称",name = "spuName")
    private String productName;

    @ApiModelProperty(value = "商品编码",name = "spuCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "关键词",name = "keyword")
    private String keyword;

    @ApiModelProperty(value = "标题",name = "title")
    private String title;

    @ApiModelProperty(value = "卖点",name = "sellPoint")
    private String sellPoint;

    @ApiModelProperty(value = "一级分类编号",name = "categoryCodeFirst")
    private String categoryCodeFirst;

    @ApiModelProperty(value = "一级分类编号",name = "categoryCodeFirst")
    private String categoryNameFirst;

    @ApiModelProperty(value = "二级分类编号",name = "categoryCodeSecond")
    private String categoryCodeSecond;

    @ApiModelProperty(value = "二级分类编号",name = "categoryNameSecond")
    private String categoryNameSecond;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    private String categoryCodeThird;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    private String categoryNameThird;

    @ApiModelProperty(value = "品牌编号",name = "brandCode")
    private String brandCode;

    @ApiModelProperty(value = "品牌名称",name = "brandCode")
    private String brandName;

    @ApiModelProperty(value = "销量",name = "saleNum")
    private Integer saleNum;

    @ApiModelProperty(value = "排序",name = "sort")
    private Long sort;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
    private String status;

    @ApiModelProperty(value = "创建人",name = "creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    private Date createdTime;

    @ApiModelProperty(value = "更新人",name = "modifier")
    private String modifier;

    @ApiModelProperty(value = "更新人编号",name = "modifierCode")
    private String modifierCode;

    @ApiModelProperty(value = "更新时间",name = "modifiedTime")
    private Date modifiedTime;

    //--- 商品详情字段 //
    @ApiModelProperty(value = "生成日期",name = "productTime")
    private Date productTime;

    @ApiModelProperty(value = "商品保质期单位",name = "shelfLifeUnit")
    private String shelfLifeUnit;

    @ApiModelProperty(value = "商品保质期",name = "shelfLife")
    private Integer shelfLife;

    @ApiModelProperty(value = "产地",name = "originPlace")
    private String originPlace;

    @ApiModelProperty(value = "重量（g）",name = "weight")
    private Integer weight;

    // 商品sku
    @ApiModelProperty(value = "sku",name = "productSku")
    private List<ProductSkuSaveEsRequest> productSku;

    @ApiModelProperty(value = "商品标签",name = "productTag")
    private List<ProductTagSaveEsRequest> productTag;
}
