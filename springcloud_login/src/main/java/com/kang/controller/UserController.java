package com.kang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kang.common.constant.AppNameConstant;
import com.kang.common.constant.Oauth2Constant;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.common.utils.JwtUtils;
import com.kang.remote.AuthServiceClient;
import com.kang.remote.BaseServiceClient;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;

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
	
	@GetMapping("/{id:\\d+}")
	public Object getUserInfo(@PathVariable Long id) {
//		Map<?,?> resultMap = restTemplate.getForObject("http://springcloud-base-kang/user/{id}", Map.class, id);
//		Map<String, Object> userMap = MapUtil.get(resultMap, "data", new TypeReference<Map<String,Object>>() {});
//		Console.log("userMap:{}",userMap);
//		return userMap;
		return baseServiceClient.getUserInfo(id);
	}
	
	@GetMapping("/getUserList")
    public Object getUserList(Map<String,Object> user) {
//		Map<?,?> resultMap = restTemplate.getForObject("http://springcloud-base-kang/user/getUserList", Map.class, user);
//		List<Map<String, Object>> userList = MapUtil.get(resultMap, "data", new TypeReference<List<Map<String,Object>>>() {});
//		Console.log("userList:{}",userList);
//		return userList;
		return baseServiceClient.getUserList(user);
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
