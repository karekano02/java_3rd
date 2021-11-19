
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * packageName    : com.kcar.fo.common.util
 * fileName       : RedisUtil

 * date           : 2021-10-27
 * description    :
 * =============================================================
 * DATE             AUTHOR          NOTE
 * -------------------------------------------------------------
 * 2021-10-27      Doo-Won Lee       최초생성
 */
@Slf4j
@Component
public  class RedisUtil {

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate<String, String> redisTemplate;


    /**
     * methodName : getData

     * description : Redis에서 key에 해당하는 값을 가져온다.
     *
     * @param key the key
     * @return object data
     */
    public String getData(String key) {
        return  redisTemplate.opsForValue().get(key);
    }

    /**
     * methodName : getData

     * description : Redis의 HashOps 에서 key에 해당하는 값을 가져온다.
     *
     * @param hashKey
     * @param key
     * @return object
     */
    public String getData(String hashKey, String key){
        return (String) redisTemplate.opsForHash().get(hashKey,key);
    }

    /**
     * methodName : setData

     * description : Redis에 정보를 저장한다.
     *
     * @param key
     * @param value
     */
    public void setData(String key, String value){ redisTemplate.opsForValue().set(key, value);  }

    /**
     * methodName : setDataExpire

     * description : 만료기간을 가진 Data를 Redis에 저장한다.
     *
     * @param key      the key
     * @param value    the value
     * @param duration the duration
     */
    public void setDataExpire(String key, String value, long duration) {
        redisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
    }


    /**
     * methodName : setAllData
     * author : Doo-Won Lee
     * description : Redis에 Map 형태의 데이터를 저장한다.
     *
     * @param hashKey
     * @param keyValueMap
     */
    public void setAllData(String hashKey, Map<String, String> keyValueMap){redisTemplate.opsForHash().putAll(hashKey, keyValueMap);  }


    /**
     * methodName : setData

     * description : Redis의 hashops에 데이터를 저장한다.
     *
     * @param hashKey
     * @param key
     * @param value
     */
    public void setData(String hashKey, String key, String value){ redisTemplate.opsForHash().put(hashKey, key, value);  }


    /**
     * methodName : deleteData

     * description : Redis에서 key에 해당하는 Data를 삭제한다.
     *
     * @param key
     * @return boolean
     */
    public boolean deleteData(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return false;
        }
    }

    /**
     * methodName : deleteData

    * description : Redis의 hashops에서 Key에 해당하는 데이터를 삭제한다.
     *
     * @param hashKey
     * @param key
     * @return long
     */
    public long deleteData(String hashKey, String key) {
        try {
            return redisTemplate.opsForHash().delete(hashKey, key);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return 0L;
        }
    }

    /**
     * methodName : deleteData
 
     * description : Redis에서 Collection 정보를 삭제한다
     *
     * @param hashKeyCollection
     */
    public void deleteData(Collection<String> hashKeyCollection) {
        try {
            redisTemplate.delete(hashKeyCollection);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
        }
    }

    /**
     * methodName : hasKey

     * description : Key에 해당하는 값이 있는지 확인
     *
     * @param key
     * @return boolean
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * methodName :  getExpire

     * description : Key에 해당하는 값의 Expire 정보를 얻는다.
     *
     * @param key  the hash key
     * @return the expire
     */
    public Long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return 0L;
        }
    }
}
