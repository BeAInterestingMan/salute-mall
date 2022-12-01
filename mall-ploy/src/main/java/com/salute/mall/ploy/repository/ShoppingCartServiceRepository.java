package com.salute.mall.ploy.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.ploy.mapper.ShoppingCartMapper;
import com.salute.mall.ploy.pojo.entity.ShoppingCart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ShoppingCartServiceRepository {
    
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * @Description 用户code查询购物车信息
     * @author liuhu
     * @param userCode
     * @date 2022/12/1 15:24
     * @return java.util.List<com.salute.mall.ploy.pojo.entity.ShoppingCart>
     */
    public List<ShoppingCart> queryByUserCode(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"用户code不能为空");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCreatorCode,userCode);
        return  shoppingCartMapper.selectList(queryWrapper);
    }

    public void save(ShoppingCart shoppingCart) {
        SaluteAssertUtil.isTrue(Objects.nonNull(shoppingCart),"参数异常");
        shoppingCartMapper.insert(shoppingCart);
    }

    public ShoppingCart getByUserCodeAndSkuCode(String userCode, String skuCode) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(userCode,skuCode),"用户code不能为空");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCreatorCode,userCode)
                    .eq(ShoppingCart::getSkuCode,skuCode);
        return  shoppingCartMapper.selectOne(queryWrapper);
    }

    public int updateShoppingCart(ShoppingCart updateShoppingCart) {
        SaluteAssertUtil.isTrue(Objects.nonNull(updateShoppingCart) &&
                !StringUtils.isAnyBlank(updateShoppingCart.getCreatorCode(),updateShoppingCart.getSkuCode()),"用户code不能为空");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCreatorCode,updateShoppingCart.getCreatorCode())
                    .eq(ShoppingCart::getSkuCode,updateShoppingCart.getSkuCode());
        return  shoppingCartMapper.update(updateShoppingCart,queryWrapper);
    }
}
