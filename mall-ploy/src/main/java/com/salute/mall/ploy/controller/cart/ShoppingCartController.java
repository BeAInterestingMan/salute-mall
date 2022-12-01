package com.salute.mall.ploy.controller.cart;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.redis.utils.Md5Utils;
import com.salute.mall.ploy.converter.ShoppingCartFaceConverter;
import com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO;
import com.salute.mall.ploy.pojo.request.AddShoppingCartRequest;
import com.salute.mall.ploy.pojo.request.UpdateShoppingCartSkuRequest;
import com.salute.mall.ploy.pojo.response.ShoppingCartPloyResponse;
import com.salute.mall.ploy.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/h5/cart/")
@Api(tags = "购物车前端控制器")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartFaceConverter shoppingCartFaceConverter;

    @Autowired
    private RedisHelper redisHelper;

    @GetMapping("getUserShoppingCart")
    @ApiOperation("获取用户的购物车信息")
    public Result<List<ShoppingCartPloyResponse>> getUserShoppingCart(){
        String userCode="wang";
        List<ShoppingCartPloyDTO> userShoppingCartList = shoppingCartService.getUserShoppingCart(userCode);
        List<ShoppingCartPloyResponse> response =  shoppingCartFaceConverter.convertToShoppingCartDetailResponseList(userShoppingCartList);
        return Result.success(response);
    }


    @PutMapping("updateShoppingCartBuyQty")
    @ApiOperation("获取用户的购物车信息")
    public Result<Void> updateShoppingCartBuyQty(@Valid UpdateShoppingCartSkuRequest request){
        String userCode="";
        shoppingCartService.updateShoppingCartBuyQty(userCode,request.getSkuCode(),request.getUpdateBuyQty());
        return Result.success();
    }

    @PutMapping("deleteShoppingCartSku")
    @ApiOperation("删除用户的购物车商品")
    public Result<Void> deleteShoppingCartSku(@Valid AddShoppingCartRequest request){
        String key = Md5Utils.getMD5(JSON.toJSONString(request).getBytes(StandardCharsets.UTF_8));
        Boolean block = redisHelper.setNx(key, "block", 1L, TimeUnit.HOURS);
        if(Objects.equals(block,Boolean.FALSE)){
            throw new BusinessException("500","请稍后点击");
        }
        String userCode="";
        shoppingCartService.addShoppingCart(userCode,request.getSkuCode(),request.getBuyQty());
        return Result.success();
    }

    @PostMapping ("addShoppingCart")
    @ApiOperation("获取用户的购物车信息")
    public Result<Void> addShoppingCart(@Valid AddShoppingCartRequest request){
        String userCode="";
        shoppingCartService.addShoppingCart(userCode,request.getSkuCode(),request.getBuyQty());
        return Result.success();
    }
}
