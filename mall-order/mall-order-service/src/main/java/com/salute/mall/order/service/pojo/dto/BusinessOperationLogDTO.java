package com.salute.mall.order.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessOperationLogDTO implements Serializable {

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    private String bizCode;

    @ApiModelProperty(value = "应用ID",name = "appId")
    private String appId;

    @ApiModelProperty(value = "场景号",name = "scene")
    private String scene;

    @ApiModelProperty(value = "日志描述",name = "logDesc")
    private String logDesc;

    @ApiModelProperty(value = "操作人名称",name = "operator")
    private String operator;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    private String operateCode;
}
