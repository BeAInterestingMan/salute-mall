package com.salute.mall.marketing.api.fallback;

import com.salute.mall.marketing.api.client.MarketingApiClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MarketingApiClientFallback implements FallbackFactory<MarketingApiClient> {

    @Override
    public MarketingApiClient create(Throwable throwable) {
        return null;
    }
}
