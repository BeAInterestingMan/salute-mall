package com.salute.mall.common.core.utils;

import java.math.BigDecimal;
import java.util.Objects;

public class BigDecimalUtil {

    public static BigDecimal divide(BigDecimal param1,BigDecimal param2){
        if(Objects.isNull(param1) || Objects.isNull(param2)){
            return BigDecimal.ZERO;
        }
        if(param2.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        return param1.divide(param2,BigDecimal.ROUND_DOWN,2);
    }
}
