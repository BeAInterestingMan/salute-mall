package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("品牌信息")
public class ProductAssociatedBrandResponse  implements Serializable {

    @ApiModelProperty(value = "品牌编号",name = "brandCode")
    private String brandCode;

    @ApiModelProperty(value = "品牌名称",name = "brandName")
    private String brandName;
}
