package com.kang.remote;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.common.constant.AppNameConstant;
import com.kang.model.User;
import com.kang.remote.hystrix.BaseServiceHystrix;

/**
　 * <p>Title: BaseServiceClient</p> 
　 * <p>Description: 引用base服务接口</p> 
　 * @author CK 
　 * @date 2019年10月21日
 */
@FeignClient(value = AppNameConstant.BASE,fallback = BaseServiceHystrix.class)
public interface BaseServiceClient{

	@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    public Map<String,Object> getUserInfo(@RequestHeader(value = "Authorization") String authorization,@PathVariable Long id);
	
	@RequestMapping(value = "/user/getUserList/{pageNo}/{pageSize}",method = RequestMethod.POST)
	public Map<String,Object> getUserList(@RequestBody User user,@PathVariable int pageNo,@PathVariable int pageSize);
}