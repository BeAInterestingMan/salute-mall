package com.salute.mall.search.pojo.dto.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductSearchAssociatedDTO implements Serializable {

    @ApiModelProperty(value = "pageSize",name = "pageSize")
    private Integer pageSize=10;

    @ApiModelProperty(value = "pageIndex",name = "pageIndex")
    private Integer pageIndex=1;

    @ApiModelProperty(value = "搜索关键字",name = "keyword")
    @Size(max = 200,message = "关键词不能超过200")
    private String keyword;

    @ApiModelProperty(value = "分类编号",name = "categoryCode")
    private String categoryCode;

    @ApiModelProperty(value = "品牌编号",name = "brandCode")
    private String brandCode;

    @ApiModelProperty(value = "排序字段 SALE_NUM-按销量排序 SALE_PRICE-按价格排序",name = "brandCode")
    private String sortType;

    @ApiModelProperty(value = "排序类型 DESC-降序 ASC-升序",name = "brandCode")
    private String sortValue;

    @ApiModelProperty(value = "价格范围-起始",name = "startSalePrice")
    @DecimalMax(value = "1000000",message = "筛选价格不能低于1000000")
    @DecimalMin(value = "0",message = "筛选价格不能低于0")
    private BigDecimal startSalePrice;

    @ApiModelProperty(value = "价格范围-终止",name = "endSalePrice")
    @DecimalMax(value = "1000000",message = "筛选价格不能低于1000000")
    @DecimalMin(value = "0",message = "筛选价格不能低于0")
    private BigDecimal endSalePrice;
}
