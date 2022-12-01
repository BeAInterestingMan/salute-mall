package com.salute.mall.ploy.service;

import com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO;

import java.util.List;

public interface ShoppingCartService {
    /**
     * @Description 获取用户购物车数据
     * @author liuhu
     * @param userCode
     * @date 2022/12/1 16:36
     * @return java.util.List<com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO>
     */
    List<ShoppingCartPloyDTO> getUserShoppingCart(String userCode);

    /**
     * @Description 加入购物车
     * @author liuhu
     * @param userCode
     * @param skuCode
     * @param buyQty
     * @date 2022/12/1 16:49
     * @return void
     */
    void addShoppingCart(String userCode, String skuCode, Integer buyQty);
    /**
     * @Description 更新加购数量
     * @author liuhu
     * @param userCode
     * @param skuCode
     * @param buyQty
     * @date 2022/12/1 17:18
     * @return void
     */
    void updateShoppingCartBuyQty(String userCode, String skuCode, Integer buyQty);
}
