package com.salute.mall.pay.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
@Data
@Configuration
@ConfigurationProperties(prefix = "alipay.config")
public class AlipayProperty implements Serializable {

    private  String gatewayUrl ="https://openapi.alipaydev.com/gateway.do";

    private final String FORMAT = "JSON";

    private final String CHARSET = "UTF-8";

    //签名方式
    private final String SIGN_TYPE = "RSA2";

    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private String notifyUrl = "http://127.0.0.1/notifyUrl";

    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private String returnUrl = "http://localhost:8080/returnUrl";

    private String appId = "2021000119693875";

    private String appPrivateKey ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcmCjXTcP0k2ftOOTr9JVo00OEOFh/v6LJ5r2gz46rBqu46BvP+0wNU5DLMNM3M/cYs1/neZXtv6z7CUgMc8as+NDmY1cr1Ow+xTyawK4Le/37/eyHSwe4+mY1+4bCvgokmc/aJboU/jZhW8mYXv+B1jCUmdURs/1dYOlBZgf6rdgOL2KbD+nQ3HXGnC2He/ivSTexq9PxrtpYnuY2zGYiziGdGD/w87lE4hg9lrke27NquF2a1keDfTwtlKlDs1fG5YV5jUuqEliDkCIWjuIMQ9d+4oEbBAalWbNYC+dIPthVBggHdqI8nUBAL4+PVQB1VI6R9B8wXMy5Hh1a3ZofAgMBAAECggEAekoputo0KY/kHVHNFKeQ9qflliAanMkfa+USOb9dfxpaiLBa5/Ur00xrSwL6L6YZVeTfBHnjKCQEBpec+HyBrxswqHrz3HsPc4W8am4GfVl7Y4tNZ+qkQUTrBUVEXFjkuuiTdZz40OQlu6peqOunRdHrZmEsEAMr3fyMrFHt8UkRGI03fQD5O59gdgvjq5Loe7WUam2OOHfxFxszNTGt9jmOrlutxMwiKhNm/96UXlsIrmgfu+hLkQOdH9XMDPnEei8IxKqlLSSpzDmuyKYxSUFt9eJ2xBBA0T8+RoqHiGQifRsA8vmHoGxYL9yVXD33oM3auPZSFTIVXM9w8l2DAQKBgQDNkAnsHj3aDrzHHxp3ONE3vY6L4RSULH+NyumgwAfMbfhZS+CzLq4tlpU3R0FUGiQSsdACjpGoSBOIKvtTXCeAeUUBjDOq1jicLuNLidW2WvMd+LRCikQ1i0ng0xd1Pod6RuHATzCJGUolIehqXb+mwExpv58c6myH5vCLvM2m/wKBgQDDBEmESChdNKAcq5EHj8yvyNKo7wNJvnio7zRo0Na2Mq/Iek0eHP9iJ1Ha9qpyVsW32BiGFabNkEpHuwlR45h6jBgG+tabm0d73HNmjeYeWaXDCAJdL0vOnQM9ZGXrXulFwnHXFf9/Y16q2neyuY9UJrBU92GeW0k7FS4eyJYs4QKBgQCkLn8+T4knOM4eXAFPZ9nyozvPDR7amg/muEKRWc7gwJSy/Z+ifY4way0S0Z6kBZz6MXrqD+TWe3reLzY9BkWmnzU2XzyFHkRouipgkt5Wn52+On3/B2AjQ/Tl1LPPuPmL+EEm0Bj7528I6WQwUGBaVwyxCZOUDMhSY2mMsmevEQKBgQCcsvEvrZthaUvOPb2HSfC6vq8mtwb5Vmc1tx9xqX0dYZUFjeAxdjNu9xE61EEZJsgwdR8ffn5NZseI460Vc7+tmKwlz6Bp4FeMGxNPMG3brUUYdXMQ1VQgbOGRjv6mJdljCF7Y6QAnp/0FZA4SOjAE+iFt5Gk4CTnKk7Y/XgXtgQKBgEGzT5aQt6OVJeiI0WTnWMVnSG+d64Z4994F+jgR0WE06qmBzQKNvIEq8aaKiYKxtO1Wg+PKqNt64ceFmZ7n2qeqdhHRNWjlhWsiv0wb2c/8RCymHkt4jU4EWaRSm11imLKRspz+aLMmmlBgtbA8jXvYzn5SohOkwqz7OfT/szr3";

    private String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmOHQlBX+oP5+Myjh1UpGAeN/ZbshNfFGY15wzevfDL7aqPJPKGRz+nPee6RnwrYb4+eeHgTvvG7JngmXKnuUXdqULfjzUXP1crPaHdEl+UK0su+UYnzgRdLmeON+cgQzQrZHm0sDCaSKLddxbEaEUZFCAOP0VVh0IlzMGdztWRVm514wFXPU1/9B8o18N3zcmB6GYTla0j5IwOdC7hnIfpZ9a3yHX5XLX/MmM+kVL/ZGi8Aqque1+vYo61E+nhb4ZfQ9SUM3YwuHL39+7KNdkn7xAur1UP/yyp5ZrQ2rmvAT8JwMOA/D9zSanqDo5djMpk5SA2Iv9xkvCg6jBomYYwIDAQAB";
}
