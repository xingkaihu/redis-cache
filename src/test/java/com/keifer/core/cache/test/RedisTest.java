package com.keifer.core.cache.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keifer.core.cache.CompositeCache;

import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-cache-redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	CompositeCache compositeCache;

	@Test
	public void set() {
		boolean result = compositeCache.set("name1", "keifer123");
		Assert.assertEquals(true, result);
	}

	@Test
	public void get() {
		String result = compositeCache.getString("name1");
		Assert.assertEquals("keifer123", result);
	}

	@Test
	public void getTargetObjct() {
		JedisPool result = compositeCache.getRedisTargetObject();
		Assert.assertNotNull(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getNodes() {
		Map<String, Object> result = compositeCache.getClusterNodes();
		Assert.assertNull(result);
	}
}
