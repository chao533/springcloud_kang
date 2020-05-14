package com.kang.remote;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kang.common.constant.AppNameConstant;
import com.kang.remote.hystrix.BaseServiceHystrix;

/**
　 * <p>Title: BaseServiceClient</p> 
　 * <p>Description: 引用base服务接口</p> 
　 * @author CK 
　 * @date 2019年10月21日
 */
@FeignClient(value = AppNameConstant.BASE,fallback = BaseServiceHystrix.class)
public interface BaseServiceClient{

	@GetMapping("/user/{id:\\d+}")
    public Map<String,Object> getUserInfo(@PathVariable Long id);
	
	 @GetMapping("/user/getUserList")
	 public Map<String,Object> getUserList(Map<String,Object> user);
}