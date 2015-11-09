package com.gcs.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * redios操作类
 * 
 * @author xubing
 * 
 */
public class RedisDao {

	private static JedisPool pool;

	/**
	 * 实例化连接池
	 */
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 链接池中最大连接数
		config.setMaxActive(200);
		// 链接池中最大空闲的连接数
		config.setMaxIdle(20);
		// 连接池中最少空闲的连接数,默认为0
		config.setMinIdle(5);
		// 当连接池资源耗尽时，调用者最大阻塞的时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时
		config.setMaxWait(1000 * 60 * 10);
		// 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取
		config.setTestOnBorrow(true);
		// 向连接池“归还”链接时，是否检测“链接”对象的有效性
		config.setTestOnReturn(true);
		// 向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除
		config.setTestWhileIdle(true);
		// 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除
		// config.setMinEvictableIdleTimeMillis(10000);
		// 当“连接池”中active数量达到阀值时，即“链接”资源耗尽时，连接池需要采取的手段, 默认为1：
		config.setWhenExhaustedAction((byte) 1);

		pool = new JedisPool(config, ReadConfigProperties.getRedisHost(),
				ReadConfigProperties.getRedisPort(), 60 * 1000);
	}

	/**
	 * 从右边获取队列
	 * 
	 * @param key
	 */
	public String getRPop(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = pool.getResource();
			value = jedis.rpop(key);
		} catch (Exception e) {
			try {
				jedis = pool.getResource();
				value = jedis.rpop(key);
			} catch (Exception e2) {
				pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				pool.returnResource(jedis);
			}
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return value;
	}

	public void setPublish(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.lpush(key, value);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
	}

	public void setLPop(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.lpush(key, value);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
	}

	public String getValue(String key) {
		String value = "";
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return value;
	}
}
