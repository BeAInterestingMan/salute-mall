package com.salute.mall.ploy.service.impl;

import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.ploy.converter.ShoppingCartServiceConverter;
import com.salute.mall.ploy.pojo.dto.ProductSkuSpecificationPloyDTO;
import com.salute.mall.ploy.pojo.dto.ShoppingCartDetailDTO;
import com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO;
import com.salute.mall.ploy.pojo.entity.ShoppingCart;
import com.salute.mall.ploy.repository.ShoppingCartServiceRepository;
import com.salute.mall.ploy.service.ShoppingCartService;
import com.salute.mall.product.api.client.ProductDetailInfoClient;
import com.salute.mall.product.api.response.ProductSkuResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartServiceRepository shoppingCartServiceRepository;

    @Autowired
    private ProductDetailInfoClient productDetailInfoClient;

    @Autowired
    private ShoppingCartServiceConverter shoppingCartServiceConverter;

    @Override
    public List<ShoppingCartPloyDTO> getUserShoppingCart(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"用户编码不能为空");
        List<ShoppingCart> shoppingCartList = shoppingCartServiceRepository.queryByUserCode(userCode);
        if(CollectionUtils.isEmpty(shoppingCartList)){
            return Lists.newArrayList();
        }
        List<String> skuCodeList = shoppingCartList.stream().map(ShoppingCart::getSkuCode).collect(Collectors.toList());
        Result<List<ProductSkuResponse>> listResult = productDetailInfoClient.queryProductSkuDetail(skuCodeList);
        SaluteAssertUtil.isTrue(Objects.nonNull(listResult) && Objects.equals(listResult.isStatus(),Boolean.TRUE),"查询商品详情失败");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(listResult.getResult()),"查询商品详情失败");
        List<ProductSkuResponse> skuInfoResponseList = listResult.getResult();
        Map<String, ProductSkuResponse> skuInfoMap = skuInfoResponseList.stream().collect(Collectors.toMap(ProductSkuResponse::getSkuCode, Function.identity(), (k1, k2) -> k1));
        Map<String, List<ShoppingCart>> shopShoppingCartMap = shoppingCartList.stream().collect(Collectors.groupingBy(v -> v.getShopCode() + "_" + v.getShopName()));
        return buildShoppingCartPloyDTOList(skuInfoMap,shopShoppingCartMap);
    }

    @Override
    public void addShoppingCart(String userCode, String skuCode, Integer buyQty) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(userCode,skuCode) && Objects.nonNull(buyQty),"参数异常");
        Result<ProductSkuResponse> result = productDetailInfoClient.getProductSkuDetail(skuCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(result) && Objects.equals(result.isStatus(),Boolean.TRUE),"查询商品详情失败");
        ProductSkuResponse ploySkuInfoResponse = result.getResult();
        SaluteAssertUtil.isTrue(Objects.nonNull(ploySkuInfoResponse),"商品已失效");
        SaluteAssertUtil.isTrue(ploySkuInfoResponse.getAvailableStock()>=buyQty,"商品库存不足");
        saveShoppingCart(userCode,buyQty,ploySkuInfoResponse);
    }

    @Override
    public void updateShoppingCartBuyQty(String userCode, String skuCode, Integer updateBuyQty) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(userCode,skuCode) && Objects.nonNull(updateBuyQty),"参数异常");
        ShoppingCart existShoppingCart = shoppingCartServiceRepository.getByUserCodeAndSkuCode(userCode,skuCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(existShoppingCart) ,"购物车不存在该商品");
        Result<ProductSkuResponse> result = productDetailInfoClient.getProductSkuDetail(skuCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(result) && Objects.equals(result.isStatus(),Boolean.TRUE),"查询商品详情失败");
        ProductSkuResponse ploySkuInfoResponse = result.getResult();
        SaluteAssertUtil.isTrue(Objects.nonNull(ploySkuInfoResponse),"商品已失效");
        SaluteAssertUtil.isTrue(ploySkuInfoResponse.getAvailableStock()>=updateBuyQty,"商品库存不足");
        updateShoppingCart(existShoppingCart,updateBuyQty);
    }

    @Override
    public void deleteShoppingCartSku(String userCode,List<String> skuCodeList) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode) && CollectionUtils.isNotEmpty(skuCodeList),"参数异常");
        shoppingCartServiceRepository.deleteByUserCodeAndSkuCodeList(userCode,skuCodeList);
    }

    @Override
    public void clearShoppingCart(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"参数异常");
        shoppingCartServiceRepository.clear(userCode);
    }

    /**
     * @Description 更新购物车数量商品
     * @author liuhu
     * @param existShoppingCart
     * @param updateBuyQty
     * @date 2022/12/1 17:31
     * @return void
     */
    private void updateShoppingCart(ShoppingCart existShoppingCart, Integer updateBuyQty) {
        ShoppingCart updateShoppingCart = new ShoppingCart();
        updateShoppingCart.setModifier("");
        updateShoppingCart.setModifierCode(existShoppingCart.getCreatorCode());
        updateShoppingCart.setSkuCode(existShoppingCart.getSkuCode());
        updateShoppingCart.setCreatorCode(existShoppingCart.getCreatorCode());
        updateShoppingCart.setBuyQty(updateBuyQty);
        shoppingCartServiceRepository.updateShoppingCart(existShoppingCart);
    }

    /**
     * @Description 新增购物车
     * @author liuhu
     * @param userCode
     * @param buyQty
     * @param ploySkuInfoResponse
     * @date 2022/12/1 17:13
     * @return void
     */
    private void saveShoppingCart(String userCode, Integer buyQty, ProductSkuResponse ploySkuInfoResponse) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCreatorCode(userCode);
        shoppingCart.setCreator("");
        shoppingCart.setModifier("");
        shoppingCart.setModifierCode(userCode);
        shoppingCart.setShopCode(ploySkuInfoResponse.getShopCode());
        shoppingCart.setShopName(ploySkuInfoResponse.getShopName());
        shoppingCart.setAddCartPrice(ploySkuInfoResponse.getSalePrice());
        shoppingCart.setSkuCode(ploySkuInfoResponse.getSkuCode());
        shoppingCart.setShopName(ploySkuInfoResponse.getShopName());
        shoppingCart.setProductCode(ploySkuInfoResponse.getProductCode());
        shoppingCart.setBuyQty(buyQty);
        shoppingCartServiceRepository.save(shoppingCart);
    }

    /**
     * @Description 构建购物车详情数据
     * @author liuhu
     * @param skuInfoMap
     * @param shopShoppingCartMap
     * @date 2022/12/1 16:36
     * @return java.util.List<com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO>
     */
    private List<ShoppingCartPloyDTO> buildShoppingCartPloyDTOList(Map<String, ProductSkuResponse> skuInfoMap,
                                                                   Map<String, List<ShoppingCart>> shopShoppingCartMap) {
        List<ShoppingCartPloyDTO> dtoList = new ArrayList<>();
        for (Map.Entry<String, List<ShoppingCart>> entry : shopShoppingCartMap.entrySet()) {
            String key = entry.getKey();
            String[] shopInfoList = key.split("_");
            ShoppingCartPloyDTO dto = new ShoppingCartPloyDTO();
            dto.setShopCode(shopInfoList[0]);
            dto.setShopName(shopInfoList[1]);
            List<ShoppingCartDetailDTO> detailDTOS = buildShoppingCartDTO(entry.getValue(), skuInfoMap);
            dto.setShoppingCartDetailList(detailDTOS);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * @Description 构建购物车商品详情
     * @author liuhu
     * @param shoppingCartList
     * @param skuInfoMap
     * @date 2022/12/1 16:31
     * @return com.salute.mall.ploy.pojo.dto.ShoppingCartDetailDTO
     */
    private List<ShoppingCartDetailDTO> buildShoppingCartDTO(List<ShoppingCart> shoppingCartList, Map<String, ProductSkuResponse> skuInfoMap) {
      return shoppingCartList.stream().map(shoppingCart->{
            ShoppingCartDetailDTO dto = shoppingCartServiceConverter.convertToShoppingCartDetailDTO(shoppingCart);
            ProductSkuResponse infoResponse = skuInfoMap.get(dto.getSkuCode());
            dto.setStatus(Boolean.TRUE);
            if(Objects.isNull(infoResponse)){
                log.info("当前商品已失效，skuCode:{}",dto.getSkuCode());
                dto.setStatus(Boolean.FALSE);
            }
            if(Objects.isNull(infoResponse.getAvailableStock()) || infoResponse.getAvailableStock()<1){
                log.info("当前商品库存不足，skuCode:{}",infoResponse.getSkuCode());
                dto.setStatus(Boolean.FALSE);
            }
            dto.setSalePrice(infoResponse.getSalePrice());
            dto.setAvailableStock(infoResponse.getAvailableStock());
            List<ProductSkuSpecificationPloyDTO> productSkuSpecificationList =  shoppingCartServiceConverter.convertToProductSkuSpecificationPloyDTOList(infoResponse.getSkuSpecificationList());
            dto.setProductSkuSpecificationList(productSkuSpecificationList);
            return dto;
        }).collect(Collectors.toList());
    }
}
