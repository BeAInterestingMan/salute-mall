package com.salute.mall.common.redis.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  @Description redis工具类
 *  @author liuhu
 *  @Date 2022/5/14 15:48
 */
public class RedisHelper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Boolean set(String key, Object value, Long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean setNx(String key, Object value, Long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
              return   redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
            } else {
               return redisTemplate.opsForValue().setIfAbsent(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean set(String key, Object value, Long time,TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true-存在，false-不存在
     */
    public boolean hasKey(String key) {
        Boolean res = redisTemplate.hasKey(key);
        if (null == res) {
            return false;
        }
        return res;
    }


    public Boolean expire(String key, Long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }



    public Boolean hSetAll(String key, Map<String,Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean hSet(String key1,String key2, Object value) {
        try {
            redisTemplate.opsForHash().put(key1,key2,value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<Object, Object> hGetAll(String key) {
       return redisTemplate.opsForHash().entries(key);
    }

    public void hDeleteAll(String key) {
         redisTemplate.opsForHash().delete(key);
    }

    public void hDelete(String key1,String key2) {
        redisTemplate.opsForHash().delete(key1,key2);
    }

    public String hGet(String key1,String key2) {
     return   (String) redisTemplate.opsForHash().get(key1,key2);
    }
}
