package com.salute.mall.product.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.enums.CommonStatusEnum;
import com.salute.mall.common.core.utils.CompletableFutureUtil;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.adapt.ProductSearchAdapt;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.adapt.dto.ProductSearchAssociatedBrandDTO;
import com.salute.mall.product.service.adapt.dto.ProductSearchAssociatedCategoryDTO;
import com.salute.mall.product.service.adapt.dto.ProductSearchAssociatedDTO;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
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

    @Resource(name = "baseProductThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private ProductTagRepository productTagRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductDetailInfoBO getProductDetail(String productCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(productCode), "????????????????????????");
        //1.???????????????????????????sku????????????????????????????????????
        CompletableFuture<Product> baseProductFuture = CompletableFuture.supplyAsync(() -> productRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductSku>> skuListFuture = CompletableFuture.supplyAsync(() -> productSkuRepository.queryByProductCode(productCode),executor);
        CompletableFutureUtil.allOf("??????????????????????????????",baseProductFuture,skuListFuture);
        Product product = baseProductFuture.join();
        List<ProductSku> productSkus = skuListFuture.join();
        SaluteAssertUtil.isTrue(Objects.nonNull(product),productCode+"???????????????");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSkus),productCode+"??????sku?????????");
        //2.???????????????????????????skuCodeList ?????????????????????????????????
        List<String> skuCodeList = productSkus.stream().map(ProductSku::getSkuCode).collect(Collectors.toList());
        CompletableFuture<List<ProductStock>> productStockListFuture = CompletableFuture.supplyAsync(() -> productStockRepository.queryBySkuCodeList(skuCodeList),executor);
        CompletableFuture<ProductDetail> productDetailFuture = CompletableFuture.supplyAsync(() -> productDetailRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductSpecification>> productSpecificationsFuture = CompletableFuture.supplyAsync(() -> productSpecificationRepository.queryByProductCode(productCode),executor);
        CompletableFuture<List<ProductTag>> productTagsFuture = CompletableFuture.supplyAsync(() ->  productTagRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductCategory>> categoryCompletableFuture = CompletableFuture.supplyAsync(() -> productCategoryRepository.queryByCategoryCode(Lists.newArrayList(product.getCategoryCodeFirst(), product.getCategoryCodeSecond(), product.getCategoryCodeThird())), executor);
        CompletableFutureUtil.allOf("????????????????????????????????????",baseProductFuture,skuListFuture);
        //3.????????????
        return  buildProductDetailInfoBO(product, productSkus,
                productStockListFuture.join(), productDetailFuture.join(),
                productSpecificationsFuture.join(),productTagsFuture.join(),
                categoryCompletableFuture.join());
    }

    /**
     * @Description ????????????
     * @author liuhu
     * @param product
     * @param productSkus
     * @param productStockList
     * @param productDetail
     * @param productSpecifications
     * @date 2022/11/29 20:42
     * @return com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO
     */
    private ProductDetailInfoBO buildProductDetailInfoBO(Product product, List<ProductSku> productSkus,
                                            List<ProductStock> productStockList,
                                            ProductDetail productDetail,
                                            List<ProductSpecification> productSpecifications,
                                            List<ProductTag> productTagList,
                                                         List<ProductCategory> productCategoryList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productStockList),product.getProductCode()+"?????????????????????");
        SaluteAssertUtil.isTrue(Objects.nonNull(productDetail),product.getProductCode()+"?????????????????????");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSpecifications),product.getProductCode()+"?????????????????????");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productCategoryList),product.getProductCode()+"???????????????????????????");
        ProductDetailInfoBO productDetailInfoBO = new ProductDetailInfoBO();
        //1.????????????????????????
        ProductBaseDTO baseInfoDTO = buildProductBaseInfoDTO(product, productSkus,productCategoryList);
        //2.??????????????????????????????
        ProductDetailBaseDTO productDetailBaseDTO = productInfoServiceConverter.convertToProductDetailInfoDTO(productDetail);
        //3.????????????sku????????????
        List<ProductSkuDTO> skuDTOList = buildSkuInfoDTOList(productSkus, productStockList);
        //4.????????????????????????
        List<ProductSpecificationDTO> productSpecificationDTOS = buildSpecificationDTOList(productSpecifications);
        List<ProductTagBaseDTO> productTagBaseDTOList =  productInfoServiceConverter.convertToProductTagBaseDTOList(productTagList);
        productDetailInfoBO.setProductBaseInfo(baseInfoDTO);
        productDetailInfoBO.setProductDetail(productDetailBaseDTO);
        productDetailInfoBO.setProductSkuList(skuDTOList);
        productDetailInfoBO.setProductSpecificationList(productSpecificationDTOS);
        productDetailInfoBO.setProductTagBaseList(productTagBaseDTOList);
        return productDetailInfoBO;
    }

    /**
     * @Description ???????????????
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
     * @Description ????????????sku??????
     * @author liuhu
     * @param productSkus
     * @param productStockList
     * @date 2022/11/29 20:16
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductSkuDTO>
     */
    private List<ProductSkuDTO> buildSkuInfoDTOList(List<ProductSku> productSkus,
                                                    List<ProductStock> productStockList) {
        Map<String, ProductStock> stockMap = productStockList.stream().collect(Collectors.toMap(ProductStock::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return  productSkus.stream().map(sku->{
            //1.??????sku??????
            ProductSkuDTO dto = productInfoServiceConverter.convertToProductPloySkuInfoDTO(sku);
            ProductStock productStock = stockMap.get(sku.getSkuCode());
            SaluteAssertUtil.isTrue(Objects.nonNull(productStock),sku.getSkuCode()+"sku?????????????????????");
            //2.????????????
            dto.setAvailableStock(productStock.getAvailableStock());
            SaluteAssertUtil.isTrue(StringUtils.isNotBlank(sku.getSpecificationJson()),sku.getSkuCode()+"sku?????????????????????");
            //3.sku??????????????? json??????
            List<ProductSkuSpecificationDTO> specificationDTOList = JSON.parseArray(sku.getSpecificationJson(), ProductSkuSpecificationDTO.class);
            dto.setSkuSpecificationList(specificationDTOList);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * @Description ????????????????????????
     * @author liuhu
     * @param product
     * @param productSkus
     * @date 2022/11/29 20:16
     * @return com.salute.mall.product.service.pojo.dto.ProductBaseDTO
     */
    private ProductBaseDTO buildProductBaseInfoDTO(Product product, List<ProductSku> productSkus,
                                                   List<ProductCategory> productCategoryList) {
        ProductBaseDTO baseInfoDTO =  productInfoServiceConverter.convertToProductBaseInfoDTO(product);
        Map<String, ProductCategory> categoryMap = productCategoryList.stream().collect(Collectors.toMap(ProductCategory::getCategoryCode, Function.identity(), (k1, k2) -> k1));
        ProductCategory first = categoryMap.get(product.getCategoryCodeFirst());
        ProductCategory second = categoryMap.get(product.getCategoryCodeFirst());
        ProductCategory third = categoryMap.get(product.getCategoryCodeFirst());
        baseInfoDTO.setCategoryNameFirst(Objects.nonNull(first)?first.getCategoryName():"");
        baseInfoDTO.setCategoryNameSecond(Objects.nonNull(second)?second.getCategoryName():"");
        baseInfoDTO.setCategoryNameThird(Objects.nonNull(third)?third.getCategoryName():"");
        ProductSku defaultSku = getDefaultSku(productSkus);
        baseInfoDTO.setSalePrice(defaultSku.getSalePrice());
        baseInfoDTO.setCostPrice(defaultSku.getCostPrice());
        baseInfoDTO.setMarketPrice(defaultSku.getMarketPrice());
        return baseInfoDTO;
    }

    @Override
    public Page<List<ProductListInfoBO>> queryProductList(ProductCustomerInfoQueryDTO infoDTO) {
        Page<List<ProductListSearchResDTO>> page = productSearchAdapt.searchProduct(infoDTO);
        if(CollectionUtils.isEmpty(page.getData())){
            return Page.emptyPage(infoDTO.getPageIndex(),infoDTO.getPageSize());
        }
        List<String> productCodeList = page.getData().stream().map(ProductListSearchResDTO::getProductCode).collect(Collectors.toList());
        List<Product> productList = productRepository.queryByProductCodeList(productCodeList);
        List<Product> sortedProductList = resortedProductList(productCodeList,productList);
        List<ProductSku> productSkus = productSkuRepository.queryByProductCodeList(productCodeList);
        List<ProductTag> productTags = productTagRepository.queryByProductCodeList(productCodeList);
        Map<String, List<ProductTag>> productTagMap = productTags.stream().collect(Collectors.groupingBy(ProductTag::getProductCode));
        Map<String, List<ProductSku>> skuMap = productSkus.stream().collect(Collectors.groupingBy(ProductSku::getProductCode));
        List<ProductListInfoBO> productListInfoBOList = sortedProductList.stream().map(v -> buildProduct(v, skuMap,productTagMap)).filter(Objects::nonNull).collect(Collectors.toList());
        return new Page<>(page.getPageIndex(), page.getPageSize(), page.getTotal(),productListInfoBOList);
    }

    /**
     * @Description ????????????????????????ES????????????
     * @author liuhu
     * @param productCodeList
     * @param productList
     * @date 2022/12/5 13:35
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.Product>
     */
    private List<Product> resortedProductList(List<String> productCodeList, List<Product> productList) {
        Map<String, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductCode, Function.identity(), (k1, k2) -> k1));
       return productCodeList.stream().map(productCode->{
            Product product = productMap.get(productCode);
            if(Objects.isNull(product)){
                log.warn("?????????????????????????????????????????????productCode???{}",productCode);
                return null;
            }
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductSkuDTO> queryProductSkuDetail(List<String> skuCodeList) {
        List<ProductSku> productSkuList = productSkuRepository.queryBySkuCodeList(skuCodeList);
        List<ProductStock> productStockList = productStockRepository.queryBySkuCodeList(skuCodeList);
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSkuList),skuCodeList+"??????sku?????????");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productStockList),skuCodeList+"?????????????????????");
        //3.????????????sku????????????
        return buildSkuInfoDTOList(productSkuList, productStockList);
    }

    @Override
    public ProductSkuDTO getProductSkuDetail(String skuCode) {
        ProductSku productSku = productSkuRepository.getBySkuCode(skuCode);
        ProductStock productStock = productStockRepository.getBySkuCode(skuCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(productSku),skuCode+"??????sku?????????");
        SaluteAssertUtil.isTrue(Objects.nonNull(productStock),skuCode+"?????????????????????");
        //3.????????????sku????????????
        ProductSkuDTO dto = productInfoServiceConverter.convertToProductPloySkuInfoDTO(productSku);
        //2.????????????
        dto.setAvailableStock(productStock.getAvailableStock());
        //3.sku??????????????? json??????
        List<ProductSkuSpecificationDTO> specificationDTOList = JSON.parseArray(productSku.getSpecificationJson(), ProductSkuSpecificationDTO.class);
        dto.setSkuSpecificationList(specificationDTOList);
        return dto;
    }

    @Override
    public ProductAssociatedDTO queryProductAssociatedInfo( ProductAssociatedQueryDTO infoDTO) {
        ProductAssociatedDTO productAssociatedDTO = ProductAssociatedDTO.builder().brandList(Lists.newArrayList()).categoryList(Lists.newArrayList()).build();
        ProductSearchAssociatedDTO associatedDTO = productSearchAdapt.searchProductAssociated(infoDTO);
        if(Objects.isNull(associatedDTO)){
            return productAssociatedDTO;
        }
        if(CollectionUtils.isNotEmpty(associatedDTO.getBrandList())){
            List<String> brandCodeList = associatedDTO.getBrandList().stream().map(ProductSearchAssociatedBrandDTO::getBrandCode).collect(Collectors.toList());
            List<ProductAssociatedBrandDTO> brandDTOS = brandCodeList.stream().map(v -> {
                ProductAssociatedBrandDTO dto = new ProductAssociatedBrandDTO();
                dto.setBrandCode(v);
                dto.setBrandName("????????????");
                return dto;
            }).collect(Collectors.toList());
            productAssociatedDTO.setBrandList(brandDTOS);
        }
        if(CollectionUtils.isNotEmpty(associatedDTO.getCategoryList())){
            List<String> categoryCodeThirdList = associatedDTO.getCategoryList().stream().map(ProductSearchAssociatedCategoryDTO::getCategoryCodeThirdCode).collect(Collectors.toList());
            List<ProductCategory> productCategoryList = productCategoryRepository.queryByCategoryCode(categoryCodeThirdList);
            List<ProductAssociatedCategoryDTO> dtoList = productInfoServiceConverter.convertToProductAssociatedCategoryDTOList(productCategoryList);
            productAssociatedDTO.setCategoryList(dtoList);
        }
        return productAssociatedDTO;
    }

    /**
     * @Description ??????????????????
     * @author liuhu
     * @param product
     * @param skuMap
     * @date 2022/11/29 17:42
     * @return com.salute.mall.product.service.pojo.bo.ProductListInfoBO
     */
    private ProductListInfoBO buildProduct(Product product,
                                           Map<String, List<ProductSku>> skuMap,
                                           Map<String, List<ProductTag>> productTagMap) {
        ProductListInfoBO productListInfoBO = new ProductListInfoBO();
        List<ProductSku> skuList = skuMap.get(product.getProductCode());
        if(CollectionUtils.isEmpty(skuList)){
           log.error("?????????????????????sku,productCode:{}",product.getProductCode());
           return null;
        }
        ProductSku defaultSku = getDefaultSku(skuList);
        productListInfoBO.setProductCode(product.getProductCode());
        productListInfoBO.setProductName(product.getProductName());
        productListInfoBO.setMainImage(product.getMainImage());
        productListInfoBO.setEvaluationNum(0L);
        productListInfoBO.setSaleNum(0L);
        productListInfoBO.setShopCode("TESH-SHOP-CODE");
        productListInfoBO.setShopName("????????????");
        productListInfoBO.setSalePrice(defaultSku.getSalePrice());
        productListInfoBO.setCostPrice(defaultSku.getCostPrice());
        productListInfoBO.setMarketPrice(defaultSku.getMarketPrice());
        productListInfoBO.setSkuCode(defaultSku.getSkuCode());
        productListInfoBO.setSkuName(defaultSku.getSkuName());
        List<ProductTag> tags = productTagMap.get(product.getProductCode());
        List<ProductTagBaseDTO> productTagBaseDTOList =   productInfoServiceConverter.convertToProductTagBaseDTOList(tags);
        productListInfoBO.setProductTagList(productTagBaseDTOList);
        return productListInfoBO;
    }

    /**
     * @Description ????????????sku
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
