package com.salute.mall.product.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.enums.CommonStatusEnum;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.adapt.ProductSearchAdapt;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.converter.ProductInfoServiceConverter;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.*;
import com.salute.mall.product.service.pojo.entity.*;
import com.salute.mall.product.service.repository.*;
import com.salute.mall.product.service.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;

    @Autowired
    private ProductInfoServiceConverter productInfoServiceConverter;

    @Autowired
    private ProductSearchAdapt productSearchAdapt;

    @Override
    public ProductDetailInfoBO getProductDetail(String productCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(productCode), "商品编号不能为空");
        Product product = productRepository.getByProductCode(productCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(product),productCode+"商品不存在");
        List<ProductSku> productSkus = productSkuRepository.queryByProductCode(product.getProductCode());
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSkus),productCode+"商品sku不存在");
        List<String> skuCodeList = productSkus.stream().map(ProductSku::getSkuCode).collect(Collectors.toList());
        List<ProductStock> productStockList = productStockRepository.queryBySkuCodeList(skuCodeList);
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productStockList),productCode+"商品库存不存在");
        ProductDetail productDetail = productDetailRepository.getByProductCode(productCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(productDetail),productCode+"商品详情不存在");
        List<ProductSpecification> productSpecifications = productSpecificationRepository.queryByProductCode(productCode);
        return buildProductH5DetailInfoBO(product, productSkus, productStockList, productDetail, productSpecifications);
    }

    /**
     * @Description 组装数量
     * @author liuhu
     * @param product
     * @param productSkus
     * @param productStockList
     * @param productDetail
     * @param productSpecifications
     * @date 2022/11/29 20:42
     * @return com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO
     */
    private ProductDetailInfoBO buildProductH5DetailInfoBO(Product product, List<ProductSku> productSkus,
                                            List<ProductStock> productStockList,
                                            ProductDetail productDetail,
                                            List<ProductSpecification> productSpecifications) {
        ProductDetailInfoBO productDetailInfoBO = new ProductDetailInfoBO();
        //1.构建商品基本信息
        ProductBaseInfoDTO baseInfoDTO = buildProductBaseInfoDTO(product, productSkus);
        //2.构建商品图文详情信息
        ProductDetailInfoDTO productDetailInfoDTO = productInfoServiceConverter.convertToProductDetailInfoDTO(productDetail);
        //3.构建商品sku聚合信息
        List<ProductPloySkuInfoDTO> ploySkuInfoDTOS = buildSkuInfoDTOList(productSkus, productStockList);
        //4.构建商品规格信息
        List<ProductSpecificationDTO> productSpecificationDTOS = buildSpecificationDTOList(productSpecifications);
        productDetailInfoBO.setProductBaseInfo(baseInfoDTO);
        productDetailInfoBO.setProductDetailInfo(productDetailInfoDTO);
        productDetailInfoBO.setProductPloySkuInfoList(ploySkuInfoDTOS);
        productDetailInfoBO.setProductSpecificationList(productSpecificationDTOS);
        return productDetailInfoBO;
    }

    /**
     * @Description 构建规格值
     * @author liuhu
     * @param productSpecifications
     * @date 2022/11/29 20:39
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductSpecificationDTO>
     */
    private List<ProductSpecificationDTO> buildSpecificationDTOList(List<ProductSpecification> productSpecifications) {
        List<ProductSpecificationDTO> productSpecificationDTOList = new ArrayList<>();
        Map<String, List<ProductSpecification>> specificationMap = productSpecifications.stream().collect(Collectors.groupingBy(ProductSpecification::getSpecificationCode));
        for (Map.Entry<String, List<ProductSpecification>> entry : specificationMap.entrySet()) {
            ProductSpecificationDTO productSpecificationDTO = new ProductSpecificationDTO();
            List<ProductSpecification> entryValue = entry.getValue();
            productSpecificationDTO.setSpecificationCode(entry.getKey());
            productSpecificationDTO.setSpecificationName(entryValue.get(0).getSpecificationName());
            productSpecificationDTO.setProductCode(entryValue.get(0).getProductCode());
            List<String> specificationValueList = entryValue.stream().map(ProductSpecification::getSpecificationValue).distinct().collect(Collectors.toList());
            productSpecificationDTO.setSpecificationValueList(specificationValueList);
            productSpecificationDTOList.add(productSpecificationDTO);
        }
        return productSpecificationDTOList;
    }

    /**
     * @Description 构建商品sku信息
     * @author liuhu
     * @param productSkus
     * @param productStockList
     * @date 2022/11/29 20:16
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO>
     */
    private List<ProductPloySkuInfoDTO> buildSkuInfoDTOList(List<ProductSku> productSkus,
                                                            List<ProductStock> productStockList) {
        Map<String, ProductStock> stockMap = productStockList.stream().collect(Collectors.toMap(ProductStock::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return  productSkus.stream().map(sku->{
            //1.基本sku信息
            ProductPloySkuInfoDTO dto = productInfoServiceConverter.convertToProductPloySkuInfoDTO(sku);
            ProductStock productStock = stockMap.get(sku.getSkuCode());
            SaluteAssertUtil.isTrue(Objects.nonNull(productStock),sku.getSkuCode()+"sku库存信息不存在");
            //2.可用库存
            dto.setAvailableStock(productStock.getAvailableStock());
            SaluteAssertUtil.isTrue(StringUtils.isNotBlank(sku.getSpecificationJson()),sku.getSkuCode()+"sku规格信息不存在");
            //3.sku的规格信息 json存储
            List<ProductSkuSpecificationDTO> specificationDTOList = JSON.parseArray(sku.getSpecificationJson(), ProductSkuSpecificationDTO.class);
            dto.setSkuSpecificationList(specificationDTOList);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * @Description 构建商品基本信息
     * @author liuhu
     * @param product
     * @param productSkus
     * @date 2022/11/29 20:16
     * @return com.salute.mall.product.service.pojo.dto.ProductBaseInfoDTO
     */
    private ProductBaseInfoDTO buildProductBaseInfoDTO(Product product, List<ProductSku> productSkus) {
        ProductBaseInfoDTO baseInfoDTO =  productInfoServiceConverter.convertToProductBaseInfoDTO(product);
        ProductSku defaultSku = getDefaultSku(productSkus);
        baseInfoDTO.setSalePrice(defaultSku.getSalePrice());
        baseInfoDTO.setCostPrice(defaultSku.getCostPrice());
        baseInfoDTO.setMarketPrice(defaultSku.getMarketPrice());
        return baseInfoDTO;
    }

    @Override
    public Page<List<ProductListInfoBO>> queryProductList(ProductListInfoDTO infoDTO) {
        Page<List<ProductListSearchResDTO>> page = productSearchAdapt.searchProduct(infoDTO);
        if(CollectionUtils.isEmpty(page.getData())){
            return Page.emptyPage(infoDTO.getPageIndex(),infoDTO.getPageSize());
        }
        List<String> productCodeList = page.getData().stream().map(ProductListSearchResDTO::getProductCode).collect(Collectors.toList());
        List<Product> productList = productRepository.queryByProductCodeList(productCodeList);
        List<ProductSku> productSkus = productSkuRepository.queryByProductCodeList(productCodeList);
        Map<String, List<ProductSku>> skuMap = productSkus.stream().collect(Collectors.groupingBy(ProductSku::getProductCode));
        List<ProductListInfoBO> productListInfoBOList = productList.stream().map(v -> buildProduct(v, skuMap)).filter(Objects::nonNull).collect(Collectors.toList());
        return new Page<>(page.getPageIndex(), page.getPageSize(), page.getTotal(),productListInfoBOList);
    }

    /**
     * @Description 构建数据对象
     * @author liuhu
     * @param product
     * @param skuMap
     * @date 2022/11/29 17:42
     * @return com.salute.mall.product.service.pojo.bo.ProductListInfoBO
     */
    private ProductListInfoBO buildProduct(Product product, Map<String, List<ProductSku>> skuMap) {
        ProductListInfoBO productListInfoBO = new ProductListInfoBO();
        List<ProductSku> skuList = skuMap.get(product.getProductCode());
        if(CollectionUtils.isEmpty(skuList)){
           log.error("当前商品无对应sku,productCode:{}",product.getProductCode());
           return null;
        }
        ProductSku defaultSku = getDefaultSku(skuList);
        productListInfoBO.setProductCode(product.getProductCode());
        productListInfoBO.setProductName(product.getProductName());
        productListInfoBO.setMainImage(product.getMainImage());
        productListInfoBO.setEvaluationNum(0L);
        productListInfoBO.setSaleNum(0L);
        productListInfoBO.setShopCode("TESH-SHOP-CODE");
        productListInfoBO.setShopName("测试店铺");
        productListInfoBO.setSalePrice(defaultSku.getSalePrice());
        productListInfoBO.setCostPrice(defaultSku.getCostPrice());
        productListInfoBO.setMarketPrice(defaultSku.getMarketPrice());
        productListInfoBO.setSkuCode(defaultSku.getSkuCode());
        productListInfoBO.setSkuName(defaultSku.getSkuName());
        return productListInfoBO;
    }

    /**
     * @Description 获取默认sku
     * @author liuhu
     * @param skuList
     * @date 2022/11/29 17:45
     * @return com.salute.mall.product.service.pojo.entity.ProductSku
     */
    private ProductSku getDefaultSku(List<ProductSku> skuList){
        List<ProductSku> defaultSkuList = skuList.stream().filter(v -> Objects.equals(v.getDefaultSkuFlag(), CommonStatusEnum.YES.getValue())).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(defaultSkuList)) {
            return defaultSkuList.get(0);
        }
        return skuList.stream().sorted(Comparator.comparing(ProductSku::getCreatedTime).reversed()).collect(Collectors.toList()).get(0);
    }

}
