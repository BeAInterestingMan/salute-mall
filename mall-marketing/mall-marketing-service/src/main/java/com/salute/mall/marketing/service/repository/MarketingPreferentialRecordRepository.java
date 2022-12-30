package com.salute.mall.marketing.service.repository;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.datasource.helper.MybatisBatchHelper;
import com.salute.mall.marketing.service.mapper.MarketingPreferentialRecordMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingPreferentialRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MarketingPreferentialRecordRepository {

    @Autowired
    private MarketingPreferentialRecordMapper preferentialRecordMapper;

    @Autowired
    private MybatisBatchHelper mybatisBatchHelper;


    public void batchInsert(List<MarketingPreferentialRecord> preferentialRecordList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(preferentialRecordList),"参数异常");
        mybatisBatchHelper.batchInsertOrUpdate(preferentialRecordList,MarketingPreferentialRecordMapper.class,(record,mapper)->mapper.insert(record));
    }
}
