package top.liyf.springboot.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 缓存配置
 *
 * @author liyf
 * Created in 2018\11\8
 */
@Configuration
@EnableCaching//启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 功能描述: 缓存管理器
     *
     * @param [redisTemplate]
     * @return org.springframework.cache.CacheManager
     * @author liyf
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间（单位:秒）
        rcm.setDefaultExpiration(60 * 60 * 24);
        return rcm;
    }

    /**
     * 功能描述: 
     * 
     * @param [factory]
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     * @author liyf
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        //或者JdkSerializationRedisSerializer序列化方式;
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        return redisTemplate;
    }

    /**
     * 功能描述: 自定义key.
     *
     * @param []
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author liyf
     */
    @Override
    public KeyGenerator keyGenerator() {
        System.out.println("RedisCacheConfig.keyGenerator()");
        return (o, method, objects) -> {
            // This will generate a unique key of the class name, the method name
            //and all method parameters appended.
            StringBuilder sb = new StringBuilder();
            String[] value = new String[1];
            // sb.append(target.getClass().getName());
            // sb.append(":" + method.getName());
            Cacheable cacheable = method.getAnnotation(Cacheable.class);
            if (cacheable != null) {
                value = cacheable.value();
            }
            CachePut cachePut = method.getAnnotation(CachePut.class);
            if (cachePut != null) {
                value = cachePut.value();
            }
            CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
            if (cacheEvict != null) {
                value = cacheEvict.value();
            }
            sb.append(value[0]);
            for (Object obj : objects) {
                sb.append(obj.toString());
            }

            return sb.toString();
        };
    }


}
