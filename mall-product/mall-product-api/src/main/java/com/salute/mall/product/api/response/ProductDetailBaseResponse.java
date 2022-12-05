package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description 商品详细信息
 *  @author liuhu
 *  @Date 2022/11/29 20:49
 */
@Data
public class ProductDetailBaseResponse implements Serializable {

    @ApiModelProperty(value = "product编号",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "生成日期",name = "productTime")
    private Date productTime;

    @ApiModelProperty(value = "商品保质期单位",name = "shelfLifeUnit")
    private String shelfLifeUnit;

    @ApiModelProperty(value = "商品保质期",name = "shelfLife")
    private Integer shelfLife;

    @ApiModelProperty(value = "产品详情网页内容",name = "detailHtml")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情",name = "detailMobileHtml")
    private String detailMobileHtml;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "图片详情",name = "images")
    private String images;

    @ApiModelProperty(value = "产地",name = "originPlace")
    private String originPlace;

    @ApiModelProperty(value = "重量（g）",name = "weight")
    private Integer weight;
}
