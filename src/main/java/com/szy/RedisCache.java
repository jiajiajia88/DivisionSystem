package com.szy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCache extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisConnectionFactory redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(timeout);
		factory.setPassword(password);
        
		factory.setPoolConfig(jedisPoolConfig);
		
		return factory;
    }
    
    @Bean
   	public <K, V> RedisTemplate<K, V> redisIntTemplate(RedisConnectionFactory factory) {
    	RedisTemplate<K,V> template = new RedisTemplate<K, V>();
    	template.setConnectionFactory(factory);
   		return template;
   	}
    
}
