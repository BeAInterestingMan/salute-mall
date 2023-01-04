package com.salute.mall.common.core.constants;

public class RedisConstants {

    public static String SALUTE_MALL = "SALUTE_MALL_";

    public  interface  AuthCacheKey{

        String MALL_AUTH_USER_INFO = SALUTE_MALL + "MALL_AUTH_USER_INFO:";
        String MALL_AUTH_TOKEN_PREFIX = SALUTE_MALL + "MALL_AUTH_TOKEN_PREFIX:";

        String MALL_AUTH_USER_PERMISSION_INFO = SALUTE_MALL + "MALL_AUTH_USER_PERMISSION_INFO:";
    }

    public  interface CartLockKey {
        String SHOPPING_CART_ADD = SALUTE_MALL + "SHOPPING_CART_ADD:";

        String SHOPPING_CART_UPDATE = SALUTE_MALL + "SHOPPING_CART_UPDATE:";

        String SHOPPING_CART_DELETE = SALUTE_MALL + "SHOPPING_CART_DELETE:";

        String SHOPPING_CART_CLEAR = SALUTE_MALL + "SHOPPING_CART_CLEAR:";

        String SHOPPING_CREATE_SALE_ORDER = SALUTE_MALL + "SHOPPING_CREATE_SALE_ORDER:";

        String SHOPPING_OPERATE_STOCK = SALUTE_MALL + "SHOPPING_OPERATE_STOCK:";

    }

    public  interface MarketingLockKey {

        String SHOPPING_MARKETING_USE_COUPON = SALUTE_MALL + "SHOPPING_MARKETING_USE_COUPON:";

        String SHOPPING_MARKETING_SEND_COUPON = SALUTE_MALL + "SHOPPING_MARKETING_SEND_COUPON:";


        String SHOPPING_MARKETING_SUBMIT_ORDER = SALUTE_MALL + "SHOPPING_MARKETING_RETURN_COUPON:";
    }

    public  interface SaleOrderLockKey {

        String SHOPPING_CREATE_SALE_ORDER = SALUTE_MALL + "SHOPPING_CREATE_SALE_ORDER:";

        String SHOPPING_DELAY_CANCEL_SALE_ORDER = SALUTE_MALL + "SHOPPING_DELAY_CANCEL_SALE_ORDER:";

    }
}
