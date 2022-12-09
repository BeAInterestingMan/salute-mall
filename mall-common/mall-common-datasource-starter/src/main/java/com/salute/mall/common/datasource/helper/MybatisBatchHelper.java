package com.salute.mall.common.datasource.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;


@Slf4j
@Component
public class MybatisBatchHelper {

    /**
     * 每次处理1000条
     */
    private static final int BATCH_SIZE = 1000;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * @Description 批处理
     * @author liuhu
     * @param recordList
     * @param mapper
     * @param biConsumer
     * @date 2022/11/22 21:59
     * @return int
     */
    public <T,V> int batchInsertOrUpdate(List<T> recordList,Class<V> mapper, BiConsumer<V,T> biConsumer){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        int updateCounts =0;
        try {
            // 获取mapper实例
            V sessionMapper = sqlSession.getMapper(mapper);
            // 数据批量处理
            for (T record : recordList) {
                 // 执行biConsumer方法
                 biConsumer.accept(sessionMapper,record);
            }
            // 输出sql  batchResults 如果sql完全一样，只是值不一样则batchResults size为1
            // 如果sql 不一样 比如update table set entity where id = ? 如果entity中有属性为null则会导致sql不一样 则batchResults size会有多个
            List<BatchResult> batchResults = sqlSession.flushStatements();
            int batchUpdateCounts = batchResults.stream().flatMapToInt(v -> Arrays.stream(v.getUpdateCounts())).sum();
            updateCounts+=batchUpdateCounts;
            // 非事务环境下强制commit，事务情况下该commit相当于无效
            sqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
        } catch (Exception e) {
            sqlSession.rollback();
            log.error("批处理执行异常",e);
            throw new RuntimeException("批处理执行异常");
        }finally {
            sqlSession.close();
        }
        return updateCounts;
    }
}
