package com.yidiandian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JedisTest extends UserServerCenterApplicationTests{

	@Test
	public void contextLoads() {
		// java客户端示例。 jedis初学者友好，操作和控制台类似
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		String result = jedis.get("hello"); // get key
		System.out.println(result);

		// pool
		JedisPool jedisPool = new JedisPool();

		// controller -- 用户关注
		// 更新某个用户的粉丝数量
		// u10001 ---> 1000
		jedis.incr("u10001"); // incr
	}

}
