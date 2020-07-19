package com.yidiandian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;


/**
 * @Author: luoxian
 * @Date: 2020/7/19 18:38
 * @Email: 15290810931@163.com
 */
// 用这个类 代替了xml文件
@Configuration
public class RedisConfigPool {
    // 创建对象，spring托管 <bean ...
    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        return jedisPool;
    }

}
