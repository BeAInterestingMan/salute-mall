package com.salute.mall.common.datasource.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.common.core.entity.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
public class PageUtil {


    public static   <S,T> Page<List<T>> convertToPage(IPage<S> source,Class<T> targetClass){
        if(Objects.isNull(source) ||Objects.isNull(targetClass) || CollectionUtils.isEmpty(source.getRecords())){
            return buildEmptyPage((int)source.getCurrent(),(int)source.getSize());
        }
        Page<List<T>> page = null;
        try {
            page = new Page<>();
            page.setPageIndex((int)source.getCurrent());
            page.setPageSize((int)source.getSize());
            page.setTotal(source.getTotal());
            List<T> targetList = source.getRecords().stream().map(record -> {
                T target = null;
                try {
                    target = targetClass.newInstance();
                } catch (Exception e) {
                    log.error("execute convertToPage  newInstance error",e);
                    return null;
                }
                BeanUtils.copyProperties(record, target);
                return target;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            page.setData(targetList);
        } catch (Exception e) {
            log.error("execute convertToPage error",e);
            return null;
        }
        return page;
    }

    public static Page buildEmptyPage(Integer pageIndex,Integer pageSize){
        Page page = new Page<>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotal(0L);
        page.setData(new ArrayList<>());
        return page;
    }
}
