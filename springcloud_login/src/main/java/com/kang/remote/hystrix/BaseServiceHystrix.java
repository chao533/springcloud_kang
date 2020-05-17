package com.kang.remote.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kang.model.User;
import com.kang.remote.BaseServiceClient;

@Component
public class BaseServiceHystrix implements BaseServiceClient{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String,Object> getUserInfo(String authorization,Long id) {
		log.info("getUserInfo服务容错:{}",id);
		return new HashMap<>();
	}

	@Override
	public Map<String, Object> getUserList(User user, int pageNo, int pageSize) {
		log.info("getUserList服务容错:{}",user);
		return new HashMap<>();
	}

	

}
