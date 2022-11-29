package com.salute.mall.product.service.pojo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("商品图文详情信息")
@Data
public class ProductDetailInfoResponse implements Serializable {

    @ApiModelProperty(value = "product编号",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "生成日期",name = "productTime")
    @TableField("product_time")
    private Date productTime;

    @ApiModelProperty(value = "商品保质期单位",name = "shelfLifeUnit")
    @TableField("shelf_life_unit")
    private String shelfLifeUnit;

    @ApiModelProperty(value = "商品保质期",name = "shelfLife")
    @TableField("shelf_life")
    private Integer shelfLife;

    @ApiModelProperty(value = "产品详情网页内容",name = "detailHtml")
    @TableField("detail_html")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情",name = "detailMobileHtml")
    @TableField("detail_mobile_html")
    private String detailMobileHtml;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

    @ApiModelProperty(value = "图片详情",name = "images")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "产地",name = "originPlace")
    @TableField("origin_place")
    private String originPlace;

    @ApiModelProperty(value = "重量（g）",name = "weight")
    @TableField("weight")
    private Integer weight;
}
