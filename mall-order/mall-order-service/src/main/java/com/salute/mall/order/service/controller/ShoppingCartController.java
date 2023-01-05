package com.salute.mall.order.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.redis.utils.Md5Utils;
import com.salute.mall.common.security.context.AuthUserContext;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.order.service.convert.ShoppingCartFaceConverter;
import com.salute.mall.order.service.pojo.dto.cart.ShoppingCartPloyDTO;
import com.salute.mall.order.service.pojo.request.AddShoppingCartRequest;
import com.salute.mall.order.service.pojo.request.DeleteShoppingCartSkuRequest;
import com.salute.mall.order.service.pojo.request.UpdateShoppingCartSkuRequest;

import com.salute.mall.order.service.pojo.response.ShoppingCartPloyResponse;
import com.salute.mall.order.service.service.ShoppingCartService;
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
@RequestMapping("/order/custom/cart/")
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
        AuthUserEntity user = AuthUserContext.getUser();
        List<ShoppingCartPloyDTO> userShoppingCartList = shoppingCartService.getUserShoppingCart(user.getUserCode());
        List<ShoppingCartPloyResponse> response =  shoppingCartFaceConverter.convertToShoppingCartDetailResponseList(userShoppingCartList);
        return Result.success(response);
    }


    @PutMapping("updateShoppingCartBuyQty")
    @ApiOperation("更新购物车信息")
    public Result<Void> updateShoppingCartBuyQty(@Valid UpdateShoppingCartSkuRequest request){
        AuthUserEntity user = AuthUserContext.getUser();
        String key = RedisConstants.CartLockKey.SHOPPING_CART_UPDATE+user.getUserCode();
        Boolean block = redisHelper.setNx(key, "block", 1L,  TimeUnit.SECONDS);
        if(Objects.equals(block,Boolean.FALSE)){
            throw new BusinessException("500","请稍后点击");
        }
        shoppingCartService.updateShoppingCartBuyQty(user.getUserCode(),request.getSkuCode(),request.getUpdateBuyQty());
        return Result.success();
    }

    @PutMapping("deleteShoppingCartSku")
    @ApiOperation("删除用户的购物车商品")
    public Result<Void> deleteShoppingCartSku(@Valid DeleteShoppingCartSkuRequest request){
        AuthUserEntity user = AuthUserContext.getUser();
        String key = RedisConstants.CartLockKey.SHOPPING_CART_DELETE+user.getUserCode()+Md5Utils.getMD5(JSON.toJSONString(request.getSkuCodeList()).getBytes(StandardCharsets.UTF_8));
        Boolean block = redisHelper.setNx(key, "block", 1L,  TimeUnit.SECONDS);
        if(Objects.equals(block,Boolean.FALSE)){
            throw new BusinessException("500","请稍后点击");
        }
        shoppingCartService.deleteShoppingCartSku(user.getUserCode(),request.getSkuCodeList());
        return Result.success();
    }

    @PostMapping ("addShoppingCart")
    @ApiOperation("添加购物车信息")
    public Result<Void> addShoppingCart(@Valid AddShoppingCartRequest request){
        AuthUserEntity user = AuthUserContext.getUser();
        String key = RedisConstants.CartLockKey.SHOPPING_CART_ADD+user.getUserCode()+request.getSkuCode();
        Boolean block = redisHelper.setNx(key, "block", 1L,  TimeUnit.SECONDS);
        if(Objects.equals(block,Boolean.FALSE)){
            throw new BusinessException("500","请稍后点击");
        }
        shoppingCartService.addShoppingCart(user.getUserCode(),request.getSkuCode(),request.getNum());
        return Result.success();
    }

    @DeleteMapping ("clearShoppingCart")
    @ApiOperation("清空购物车信息")
    public Result<Void> clearShoppingCart(){
        AuthUserEntity user = AuthUserContext.getUser();
        String key = RedisConstants.CartLockKey.SHOPPING_CART_CLEAR+user.getUserCode();
        Boolean block = redisHelper.setNx(key, "block", 1L, TimeUnit.MINUTES);
        if(Objects.equals(block,Boolean.FALSE)){
            throw new BusinessException("500","请稍后点击");
        }
        shoppingCartService.clearShoppingCart(user.getUserCode());
        return Result.success();
    }
}
