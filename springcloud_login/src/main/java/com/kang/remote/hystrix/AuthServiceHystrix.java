package com.kang.remote.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kang.remote.AuthServiceClient;

/**
 * Created with IntelliJ IDEA.
 * User: jc
 * Date: 2018/6/27
 * Time: 上午11:49
 */

@Component
public class AuthServiceHystrix implements AuthServiceClient {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public Map<String,Object> getToken(String authorization, String type, String username, String password) {
    	log.info("getToken服务容错:{}",username);
        return new HashMap<>();
    }

    @Override
    public Map<String,Object> refreshToken(String authorization, String type, String refreshToken) {
    	log.info("refreshToken服务容错:{}",refreshToken);
        return new HashMap<>();
    }
}
