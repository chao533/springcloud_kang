package com.kang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kang.common.constant.AppNameConstant;
import com.kang.common.constant.Oauth2Constant;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.common.utils.JwtUtils;
import com.kang.model.User;
import com.kang.remote.AuthServiceClient;
import com.kang.remote.BaseServiceClient;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
	private BaseServiceClient baseServiceClient;
	@Autowired
	private AuthServiceClient authServiceClient;

	@GetMapping("/index")
	public Message<?> index() {
		String authorization = "Basic " + Base64.encode(Oauth2Constant.CLIENT + ":" + Oauth2Constant.SECRET, CharsetUtil.CHARSET_UTF_8);
		Console.log("authorization:{}",authorization);
		Map<String,Object> result = new HashMap<>();
		result.put("getUser", JwtUtils.getUser());
		Map<String,Object> token = authServiceClient.getToken(authorization, Oauth2Constant.GRANT_TYPES[0], "admin", "123456");
		result.put("getToken", token);
		Map<String,Object> refrshToken = authServiceClient.refreshToken(authorization, Oauth2Constant.GRANT_TYPES[1], MapUtil.getStr(token, "refresh_token"));
		result.put("refrshToken", refrshToken);
		return new Message<>(ErrorCode.SUCCESS,result);
	}
	
	/**
	 * <p>Title: getUserInfo</p>
	 * <p>Description: Feign和Ribbon测试成功</p>
	 * @param @param id
	 * @param @return
	 */
	@RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
	public Object getUserInfo(@PathVariable Long id) {
		Map<?,?> resultMap = restTemplate.getForObject(AppNameConstant.BASE_ADDR + "/user/{id}", Map.class, id);
		Map<String, Object> userMap = MapUtil.get(resultMap, "data", new TypeReference<Map<String,Object>>() {});
		Console.log("userMap:{}",userMap);
//		return userMap;
		return baseServiceClient.getUserInfo(JwtUtils.getRequest().getHeader(Oauth2Constant.TOKEN_HEADER), id);
	}
	
	/**
	 * <p>Title: getUserList</p>
	 * <p>Description: Feign测试成功</p>
	 * @param @param user
	 * @param @param pageNo
	 * @param @param pageSize
	 * @param @return
	 */
	@RequestMapping(value = "/getUserList/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public Object getUserList(@RequestBody User user,@PathVariable int pageNo,@PathVariable int pageSize) {
		return baseServiceClient.getUserList(user,pageNo,pageSize);
	}
	
	@PostMapping("/addUser")
    public Message<?> addUser(@RequestBody Map<String,Object> user) {
    	HttpStatus status = restTemplate.postForEntity(AppNameConstant.BASE_ADDR + "/user/addUser", user,null).getStatusCode();
    	if (status.is2xxSuccessful()) {
            return new Message<>(ErrorCode.SUCCESS);
        } else {
        	return new Message<>(ErrorCode.ERROR);
        }
	}
	
	@PutMapping("/updateUser")
    public Message<?> updateUser(@RequestBody Map<String,Object> user) {
		restTemplate.put(AppNameConstant.BASE_ADDR + "/user/updateUser", user);
		return new Message<>(ErrorCode.SUCCESS);
    }
	
	@DeleteMapping("/{id:\\d+}")
    public Message<?> deleteUser(@PathVariable Long id) {
    	restTemplate.delete(AppNameConstant.BASE_ADDR + "/user/deleteUser", id);
    	return new Message<>(ErrorCode.SUCCESS);
    }
}
