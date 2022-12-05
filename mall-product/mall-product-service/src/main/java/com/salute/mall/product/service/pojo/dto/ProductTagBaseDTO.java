package com.salute.mall.product.service.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ProductTagBaseDTO implements Serializable {

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "标签code",name = "tagCode")
    @TableField("tag_code")
    private String tagCode;

    @ApiModelProperty(value = "标签名称",name = "tagName")
    @TableField("tag_name")
    private String tagName;

}
