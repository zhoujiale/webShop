package com.zjl.webshop.Redis;/**
 * @Auther: zhou
 * @Date: 2018/12/28 11:17
 * @Description:
 */

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName RedisUtil
 *@Description Redis工具类
 *@Author zhou
 *Date 2018/12/28 11:17
 *@Version 1.0
 **/
public class RedisUtil {

    public RedisTemplate<String,Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    /**
     * @description 指定缓存失效时间
     * @author zhou
     * @created  2018/12/28 11:20    
     * @param key 键
     * @param time 时间（秒）
     * @return 
     */
    public boolean expire(String key,Long time){
        try{
            if(time>0){
               redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 根据key获取过期时间
     * @author zhou
     * @created  2018/12/28 11:23    
     * @param key 键
     * @return 
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * @description 判断key是否存在
     * @author zhou
     * @created  2018/12/28 11:25    
     * @param key 键
     * @return 
     */
    public boolean hashKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 删除缓存
     * @author zhou
     * @created  2018/12/28 11:29    
     * @param key
     * @return 
     */
    public void deleteKey(String ... key){
        if(key != null && key.length>0){
           if(key.length == 1){
              redisTemplate.delete(key[0]);
           }else {
               redisTemplate.delete(CollectionUtils.arrayToList(key));
           }
        }
    }

    /**
     * @description 普通缓存获取
     * @author zhou
     * @created  2018/12/28 11:33    
     * @param key 键
     * @return 
     */
    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }


    /**
     * @description 普通缓存放入
     * @author zhou
     * @created  2018/12/28 11:35    
     * @param key 键
     * @param value 值
     * @return 
     */
    public boolean set(String key,Object value){
       try{
          redisTemplate.opsForValue().set(key,value);
          return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    /**
     * @description 普通缓存放入设置时间
     * @author zhou
     * @created  2018/12/28 11:42    
     * @param 
     * @return 
     */
    public boolean set(String key,Object value,Long time){
        try{
            if(time > 0){
               redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 递增
     * @author zhou
     * @created  2018/12/28 11:54    
     * @param key 键
     * @param delta 递增因子
     * @return 
     */
    public Long increment(String key,Long delta){
        if(delta<0){
           throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * @description 递减
     * @author zhou
     * @created  2018/12/28 11:57    
     * @param key 键
     * @param delta 递减因子
     * @return
     */
    public Long decrement(String key,Long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    /**
     * @description hash获取键值
     * @author zhou
     * @created  2018/12/28 13:34    
     * @param key
     * @param item
     * @return 
     */
    public Object hashGet(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * @description 获取hash对应的所有键值
     * @author zhou
     * @created  2018/12/28 13:40    
     * @param key
     * @return 
     */
    public Map<Object,Object> hashMapGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @description 存储一个map
     * @author zhou
     * @created  2018/12/28 13:46    
     * @param key 键
     * @param map
     * @return 
     */
    public boolean hashMapSet(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 存储一个map设置时间
     * @author zhou
     * @created  2018/12/28 13:49    
     * @param key
     * @param map
     * @param time
     * @return 
     */
    public boolean hashMapSet(String key,Map<String,Object> map,Long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
               expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 向一张hash表中放入数据,如果不存在创建
     * @author zhou
     * @created  2018/12/28 14:25    
     * @param key
     * @param item
     * @param value
     * @return 
     */
    public boolean hashSet(String key,String item,Object value){
        try{
             redisTemplate.opsForHash().put(key,item,value);
             return true;
        }catch (Exception e){
             e.printStackTrace();
             return false;
        }
    }

    /**
     * @description 向一张hash表中放入数据,如果不存在创建
     * @author zhou
     * @created  2018/12/28 14:29    
     * @param key
     * @param item
     * @param value
     * @param time
     * @return 
     */
    public boolean hashSet(String key,String item,Object value,Long time){
        try{
           redisTemplate.opsForHash().put(key,item,value);
           if(time>0){
               expire(key,time);
           }
           return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 删除hash表中的值
     * @author zhou
     * @created  2018/12/28 14:26    
     * @param 
     * @return 
     */
    public void hashDelete(String key,Object ...item){
           redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @description 判断hash表中是否有该项的值
     * @author zhou
     * @created  2018/12/28 14:32
     * @param key
     * @param item
     * @return 
     */
    public boolean hashKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     * @description hash递增
     * @author zhou
     * @created  2018/12/28 14:37    
     * @param key 键
     * @param item 项
     * @param by 要增加几
     * @return 
     */
    public double hashIncrement(String key,String item,Double by){
        return redisTemplate.opsForHash().increment(key,item,by);
    }

    /**
     * @description hash递减
     * @author zhou
     * @created  2018/12/28 14:45    
     * @param key
     * @param by
     * @param item
     * @return 
     */
    public double hashDecrement(String key,String item,Double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    /**
     * @description 根据key获取Set中的所有值
     * @author zhou
     * @created  2018/12/28 14:50    
     * @param 
     * @return 
     */
    public Set<Object> hashGet(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 根据value从一个set中查询,是否存在
     * @author zhou
     * @created  2018/12/28 14:54    
     * @param key
     * @param value
     * @return 
     */
    public boolean setHashKey(String key,Object value){
        try{
            return redisTemplate.opsForSet().isMember(key,value);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long putSet(String key,Object ...values){
        try{
             return redisTemplate.opsForSet().add(key,values);
        }catch (Exception e){
             e.printStackTrace();
             return 0;
        }
    }

    /**
     * @description 将set数据放入缓存
     * @author zhou
     * @created  2018/12/28 15:03    
     * @param 
     * @return 
     */
    public long putSetAndTime(String key,Long time,Object ...values){
        try{
            Long count = redisTemplate.opsForSet().add(key,values);
            if(time>0) {
                expire(key,time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description 移除值为value的键
     * @author zhou
     * @created  2018/12/28 15:05    
     * @param key
     * @param values 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key,Object ...values){
        try{
            Long count = redisTemplate.opsForSet().remove(key,values);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description 获取list缓存的内容
     * @author zhou
     * @created  2018/12/28 15:11    
     * @param key
     * @param start
     * @param end
     * @return 
     */
    public List<Object> listGet(String key,Long start,Long end){
        try{
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 获取list缓存的长度
     * @author zhou
     * @created  2018/12/28 15:25    
     * @param key
     * @return 
     */
    public Long listGetSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return Long.valueOf(0);
        }
    }

    /**
     * @description 通过索引获取list中的值
     * @author zhou
     * @created  2018/12/28 15:26    
     * @param key
     * @param index index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return 
     */
    public Object listGetIndex(String key,Long index){
        try{
            return redisTemplate.opsForList().index(key,index);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 将list放入缓存
     * @author zhou
     * @created  2018/12/28 15:30    
     * @param key
     * @param value
     * @return 
     */
    public boolean listSet(String key,Object value){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 将list放入缓存，设置时间
     * @author zhou
     * @created  2018/12/28 15:33    
     * @param key
     * @param value
     * @param time
     * @return 
     */
    public boolean listSet(String key,Object value,Long time){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            if(time>0){
                expire(key,time);
            }
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 将list放入缓存
     * @author zhou
     * @created  2018/12/28 15:35    
     * @param key
     * @param value
     * @return 
     */
    public boolean listSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 将list放入缓存设置时间
     * @author zhou
     * @created  2018/12/28 15:36    
     * @param key
     * @param value
     * @param time
     * @return 
     */
    public boolean listSet(String key, List<Object> value, Long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 根据索引修改list的数据
     * @author zhou
     * @created  2018/12/28 15:38    
     * @param key
     * @param index
     * @param value
     * @return 
     */
    public boolean listUpdateIndex(String key,Long index,Object value){
         try{
             redisTemplate.opsForList().set(key,index,value);
             return true;
         }catch (Exception e){
             e.printStackTrace();
             return false;
         }
    }

    /**
     * @description 移除N个值为value的键
     * @author zhou
     * @created  2018/12/28 15:44    
     * @param key
     * @param count
     * @param value
     * @return 
     */
    public Long listRemove(String key,Long count,Object value){
        try{
            Long remove = redisTemplate.opsForList().remove(key,count,value);
            return remove;
        }catch (Exception e){
            e.printStackTrace();
            return Long.valueOf(0);
        }
    }


}
