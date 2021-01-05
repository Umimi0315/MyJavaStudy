package com.baizhi.cache;

import com.baizhi.util.ApplicationContextUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

//自定义Redis缓存实现
public class RedisCache implements org.apache.ibatis.cache.Cache {

    //当前放入缓存的mapper的namespace
    private final String id;

    //必须存在构造方法
    public RedisCache(String id) {
        this.id = id;
    }

    //返回cache的唯一标识
    @Override
    public String getId() {
        return id;
    }

    //缓存放入值
    @Override
    public void putObject(Object o, Object o1) {
        System.out.println("key:"+o);
        System.out.println("value:"+o1);
//        //通过application工具类获取redisTempalte
//        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //使用redishash类型作为缓存存储模型 key hashkey value
        getRedisTempalte().opsForHash().put(id.toString(), getKeyToMD5(o.toString()), o1);
    }

    //缓存中获取数据
    @Override
    public Object getObject(Object o) {

        return getRedisTempalte().opsForHash().get(id.toString(), getKeyToMD5(o.toString()));
    }

    //注意：这个方法为mybatis的保留方法，默认没有实现，后续版本可能会实现
    @Override
    public Object removeObject(Object o) {
        System.out.println("根据指定key删除缓存");
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存~~~~");

        //清空namespace
        getRedisTempalte().delete(id.toString());//清空缓存

    }

    //用来计算缓存的数量
    @Override
    public int getSize() {

        return getRedisTempalte().opsForHash().size(id.toString()).intValue();
    }

    //封装redis
    private RedisTemplate getRedisTempalte(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    //封装一个对key进行md5处理的方法
    private String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //...指定不同的业务模块设置不同的缓存超时时间
}
