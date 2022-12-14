package com.salute.mall.common.core.constants;

public class RedisConstants {

    public static String SALUTE_MALL = "SALUTE_MALL_";

    public  interface  LockKey{
        String SHOPPING_CART_ADD = SALUTE_MALL + "SHOPPING_CART_ADD:";

        String SHOPPING_CART_UPDATE = SALUTE_MALL + "SHOPPING_CART_UPDATE:";

        String SHOPPING_CART_DELETE = SALUTE_MALL + "SHOPPING_CART_DELETE:";

        String SHOPPING_CART_CLEAR = SALUTE_MALL + "SHOPPING_CART_CLEAR:";
    }
}
