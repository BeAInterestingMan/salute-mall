package com.salute.mall.order.service.pojo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartDetailResponse implements Serializable {

    @ApiModelProperty(value = "商品编码",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "商品状态 false 失效 true 有效",name = "productCode")
    private Boolean status;

    @ApiModelProperty(value = "sku主图",name = "mainImage")
    private String availableStock;

    @ApiModelProperty(value = "sku主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "商品sku编码",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "商品sku名称",name = "skuCode")
    private String skuName;

    @ApiModelProperty(value = "加入购物车时价格",name = "addCartPrice")
    private BigDecimal addCartPrice;

    @ApiModelProperty(value = "实时售卖价格",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "创建人",name = "creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    @TableField("creator_code")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    private Date createdTime;

    @ApiModelProperty(value = "sku规格信息",name = "productSkuSpecificationList")
    private List<ProductSkuSpecificationResponse> productSkuSpecificationList;

}
