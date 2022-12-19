package com.salute.mall.user.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
@TableName("user_role")
@ApiModel(value = "用户角色表")
public class UserRole implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "角色编号",name = "roleCode")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "用户编号",name = "userCode")
    @TableField("user_code")
    private String userCode;


}
