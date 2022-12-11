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
import com.salute.mall.product.service.converter.ProductServiceConverter;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.*;
import com.salute.mall.product.service.pojo.entity.*;
import com.salute.mall.product.service.repository.*;
import com.salute.mall.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class ProductServiceImpl implements ProductService {

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
    private ProductServiceConverter productServiceConverter;

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
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(productCode), "商品编号不能为空");
        //1.查询商品基本信息和sku信息放一个流程中同时完成
        CompletableFuture<Product> baseProductFuture = CompletableFuture.supplyAsync(() -> productRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductSku>> skuListFuture = CompletableFuture.supplyAsync(() -> productSkuRepository.queryByProductCode(productCode),executor);
        CompletableFutureUtil.allOf("异步获取商品信息异常",baseProductFuture,skuListFuture);
        Product product = baseProductFuture.join();
        List<ProductSku> productSkus = skuListFuture.join();
        SaluteAssertUtil.isTrue(Objects.nonNull(product),productCode+"商品不存在");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSkus),productCode+"商品sku不存在");
        //2.查询库存需要依赖与skuCodeList 其他的放一个流程中完成
        List<String> skuCodeList = productSkus.stream().map(ProductSku::getSkuCode).collect(Collectors.toList());
        CompletableFuture<List<ProductStock>> productStockListFuture = CompletableFuture.supplyAsync(() -> productStockRepository.queryBySkuCodeList(skuCodeList),executor);
        CompletableFuture<ProductDetail> productDetailFuture = CompletableFuture.supplyAsync(() -> productDetailRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductSpecification>> productSpecificationsFuture = CompletableFuture.supplyAsync(() -> productSpecificationRepository.queryByProductCode(productCode),executor);
        CompletableFuture<List<ProductTag>> productTagsFuture = CompletableFuture.supplyAsync(() ->  productTagRepository.getByProductCode(productCode),executor);
        CompletableFuture<List<ProductCategory>> categoryCompletableFuture = CompletableFuture.supplyAsync(() -> productCategoryRepository.queryByCategoryCode(Lists.newArrayList(product.getCategoryCodeFirst(), product.getCategoryCodeSecond(), product.getCategoryCodeThird())), executor);
        CompletableFutureUtil.allOf("异步获取商品附加信息异常",baseProductFuture,skuListFuture);
        //3.构建数据
        return  buildProductDetailInfoBO(product, productSkus,
                productStockListFuture.join(), productDetailFuture.join(),
                productSpecificationsFuture.join(),productTagsFuture.join(),
                categoryCompletableFuture.join());
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
    private ProductDetailInfoBO buildProductDetailInfoBO(Product product, List<ProductSku> productSkus,
                                                         List<ProductStock> productStockList,
                                                         ProductDetail productDetail,
                                                         List<ProductSpecification> productSpecifications,
                                                         List<ProductTag> productTagList,
                                                         List<ProductCategory> productCategoryList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productStockList),product.getProductCode()+"商品库存不存在");
        SaluteAssertUtil.isTrue(Objects.nonNull(productDetail),product.getProductCode()+"商品详情不存在");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSpecifications),product.getProductCode()+"商品规格不存在");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productCategoryList),product.getProductCode()+"商品分类信息不存在");
        ProductDetailInfoBO productDetailInfoBO = new ProductDetailInfoBO();
        //1.构建商品基本信息
        ProductBaseDTO baseInfoDTO = buildProductBaseInfoDTO(product, productSkus,productCategoryList,productDetail);
        //3.构建商品sku聚合信息
        List<ProductSkuDTO> skuDTOList = buildSkuInfoDTOList(productSkus, productStockList);
        //4.构建商品规格信息
        List<ProductSpecificationDTO> productSpecificationDTOS = buildSpecificationDTOList(productSpecifications);
        List<ProductTagBaseDTO> productTagBaseDTOList =  productServiceConverter.convertToProductTagBaseDTOList(productTagList);
        productDetailInfoBO.setProductDetail(baseInfoDTO);
        productDetailInfoBO.setProductSkuList(skuDTOList);
        productDetailInfoBO.setProductSpecificationList(productSpecificationDTOS);
        productDetailInfoBO.setProductTagBaseList(productTagBaseDTOList);
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
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductSkuDTO>
     */
    private List<ProductSkuDTO> buildSkuInfoDTOList(List<ProductSku> productSkus,
                                                    List<ProductStock> productStockList) {
        Map<String, ProductStock> stockMap = productStockList.stream().collect(Collectors.toMap(ProductStock::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return  productSkus.stream().map(sku->{
            //1.基本sku信息
            ProductSkuDTO dto = productServiceConverter.convertToProductPloySkuInfoDTO(sku);
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
     * @return com.salute.mall.product.service.pojo.dto.ProductBaseDTO
     */
    private ProductBaseDTO buildProductBaseInfoDTO(Product product,
                                                   List<ProductSku> productSkus,
                                                   List<ProductCategory> productCategoryList,
                                                   ProductDetail productDetail) {
        ProductBaseDTO baseInfoDTO =  productServiceConverter.convertToProductBaseInfoDTO(product);
        BeanUtils.copyProperties(productDetail,baseInfoDTO);
        Map<String, ProductCategory> categoryMap = productCategoryList.stream().collect(Collectors.toMap(ProductCategory::getCategoryCode, Function.identity(), (k1, k2) -> k1));
        ProductCategory first = categoryMap.get(product.getCategoryCodeFirst());
        ProductCategory second = categoryMap.get(product.getCategoryCodeSecond());
        ProductCategory third = categoryMap.get(product.getCategoryCodeThird());
        baseInfoDTO.setCategoryNameFirst(Objects.nonNull(first)?first.getCategoryName():"");
        baseInfoDTO.setCategoryNameSecond(Objects.nonNull(second)?second.getCategoryName():"");
        baseInfoDTO.setCategoryNameThird(Objects.nonNull(third)?third.getCategoryName():"");
        ProductSku defaultSku = getDefaultSku(productSkus);
        baseInfoDTO.setSalePrice(defaultSku.getSalePrice());
        baseInfoDTO.setCostPrice(defaultSku.getCostPrice());
        baseInfoDTO.setMarketPrice(defaultSku.getMarketPrice());
        List<String> skuImageList = productSkus.stream().map(ProductSku::getMainImage).collect(Collectors.toList());
        baseInfoDTO.setProductImageList(skuImageList);
        baseInfoDTO.setAvailableStock(100);
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
     * @Description 数据库的排序按照ES排序填充
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
                log.warn("当前商品编号查询不到商品信息，productCode：{}",productCode);
                return null;
            }
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductSkuDTO> queryProductSkuDetail(List<String> skuCodeList) {
        List<ProductSku> productSkuList = productSkuRepository.queryBySkuCodeList(skuCodeList);
        List<ProductStock> productStockList = productStockRepository.queryBySkuCodeList(skuCodeList);
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productSkuList),skuCodeList+"商品sku不存在");
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productStockList),skuCodeList+"商品库存不存在");
        //3.构建商品sku聚合信息
        return buildSkuInfoDTOList(productSkuList, productStockList);
    }

    @Override
    public ProductSkuDTO getProductSkuDetail(String skuCode) {
        ProductSku productSku = productSkuRepository.getBySkuCode(skuCode);
        ProductStock productStock = productStockRepository.getBySkuCode(skuCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(productSku),skuCode+"商品sku不存在");
        SaluteAssertUtil.isTrue(Objects.nonNull(productStock),skuCode+"商品库存不存在");
        //3.构建商品sku聚合信息
        ProductSkuDTO dto = productServiceConverter.convertToProductPloySkuInfoDTO(productSku);
        //2.可用库存
        dto.setAvailableStock(productStock.getAvailableStock());
        //3.sku的规格信息 json存储
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
                dto.setBrandName("测试品牌");
                return dto;
            }).collect(Collectors.toList());
            productAssociatedDTO.setBrandList(brandDTOS);
        }
        if(CollectionUtils.isNotEmpty(associatedDTO.getCategoryList())){
            List<String> categoryCodeThirdList = associatedDTO.getCategoryList().stream().map(ProductSearchAssociatedCategoryDTO::getCategoryCodeThirdCode).collect(Collectors.toList());
            List<ProductCategory> productCategoryList = productCategoryRepository.queryByCategoryCode(categoryCodeThirdList);
            List<ProductAssociatedCategoryDTO> dtoList = productServiceConverter.convertToProductAssociatedCategoryDTOList(productCategoryList);
            productAssociatedDTO.setCategoryList(dtoList);
        }
        return productAssociatedDTO;
    }

    /**
     * @Description 构建数据对象
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
           log.error("当前商品无对应sku,productCode:{}",product.getProductCode());
           return null;
        }
        ProductSku defaultSku = getDefaultSku(skuList);
        productListInfoBO.setProductCode(product.getProductCode());
        productListInfoBO.setProductName(product.getProductName());
        productListInfoBO.setMainImage(defaultSku.getMainImage());
        productListInfoBO.setEvaluationNum(0L);
        productListInfoBO.setSaleNum(0L);
        productListInfoBO.setShopCode("TESH-SHOP-CODE");
        productListInfoBO.setShopName("测试店铺");
        productListInfoBO.setSalePrice(defaultSku.getSalePrice());
        productListInfoBO.setCostPrice(defaultSku.getCostPrice());
        productListInfoBO.setMarketPrice(defaultSku.getMarketPrice());
        productListInfoBO.setSkuCode(defaultSku.getSkuCode());
        productListInfoBO.setSkuName(defaultSku.getSkuName());
        List<ProductTag> tags = productTagMap.get(product.getProductCode());
        List<ProductTagBaseDTO> productTagBaseDTOList =   productServiceConverter.convertToProductTagBaseDTOList(tags);
        productListInfoBO.setProductTagList(productTagBaseDTOList);
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
