package com.szy.util;

import com.alibaba.fastjson.JSON;
import com.szy.ApplicationStart;
import com.szy.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheUtil {
	public static final String SESSION_PREF = "S_";
	public static final long SESSION_EXPIRE = 24 * 3600;

	public static final long PASSWD_EXPIRE = 60 * 60;

	public static CacheUtil create(){
		return ApplicationStart.getBean(CacheUtil.class);
	}
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void delSeeion(String ss){
		redisTemplate.delete(SESSION_PREF + ss);
	}

	public void setSession(Session session, long expire){
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			String key = SESSION_PREF + session.getKey();
			operations.set(key, JSON.toJSONString(session),expire,TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Session getSession(String ss){
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			String val = operations.get(SESSION_PREF + ss);
			if(val == null){
				return null;
			}
			return JSON.parseObject(val, Session.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSession(Session session){
		setSession(session,SESSION_EXPIRE);
	}

}
