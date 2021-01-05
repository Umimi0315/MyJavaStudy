package com.atguigu.cache.config;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //CacheManagerCustomizers可以来定制缓存的一些规则
    @Primary    //将某个缓存管理器作为默认的
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> employeeRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        //key多了一个前缀

        //使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //CacheManagerCustomizers可以来定制缓存的一些规则
    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> employeeRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        //key多了一个前缀

        //使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
