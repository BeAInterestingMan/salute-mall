package com.salute.mall.product.service.controller.stock;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import com.salute.mall.product.service.converter.ProductStockFaceConverter;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.service.ProductStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stock/api")
@Slf4j
@Api(tags = "库存api接口")
public class ProductStockApiController {

    @Autowired
    private ProductStockService productStockService;

    @Autowired
    private ProductStockFaceConverter productStockFaceConverter;

    @PostMapping("operateFreezeStock")
    @ApiOperation("操作商品冻结库存")
    public Result<Void> operateFreezeStock(@Valid @RequestBody OperateFreezeStockRequest request){
        log.info("execute operateFreezeStock info,req:{}", JSON.toJSONString(request));
        OperateFreezeStockDTO dto = productStockFaceConverter.convertToOperateFreezeStockDTO(request);
        productStockService.operateFreezeStock(dto);
        log.info("execute operateFreezeStock info,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(""));
        return Result.success();
    }
}
