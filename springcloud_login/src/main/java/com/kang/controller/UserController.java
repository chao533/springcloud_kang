package com.kang.controller;

import java.util.List;
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

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.common.utils.JwtUtils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private RestTemplate restTemplate;

	@GetMapping("/index")
	public Message<?> index() {
	    return new Message<>(ErrorCode.SUCCESS,JwtUtils.getUser());
	}
	
	@GetMapping("/{id:\\d+}")
	public Object getUserInfo(@PathVariable Long id) {
		Map<?,?> resultMap = restTemplate.getForObject("http://springcloud-base-kang/user/{id}", Map.class, id);
		Map<String, Object> userMap = MapUtil.get(resultMap, "data", new TypeReference<Map<String,Object>>() {});
		Console.log("userMap:{}",userMap);
		return userMap;
	}
	
	@GetMapping("/getUserList")
    public Object getUserList(Map<String,Object> user) {
		Map<?,?> resultMap = restTemplate.getForObject("http://springcloud-base-kang/user/getUserList", Map.class, user);
		List<Map<String, Object>> userList = MapUtil.get(resultMap, "data", new TypeReference<List<Map<String,Object>>>() {});
		Console.log("userList:{}",userList);
		return userList;
	}
	
	@PostMapping("/addUser")
    public Message<?> addUser(@RequestBody Map<String,Object> user) {
    	HttpStatus status = restTemplate.postForEntity("http://springcloud-base-kang/user/addUser", user,null).getStatusCode();
    	if (status.is2xxSuccessful()) {
            return new Message<>(ErrorCode.SUCCESS);
        } else {
        	return new Message<>(ErrorCode.ERROR);
        }
	}
	
	@PutMapping("/updateUser")
    public Message<?> updateUser(@RequestBody Map<String,Object> user) {
		restTemplate.put("http://springcloud-base-kang/user/updateUser", user);
		return new Message<>(ErrorCode.SUCCESS);
    }
	
	@DeleteMapping("/{id:\\d+}")
    public Message<?> deleteUser(@PathVariable Long id) {
    	restTemplate.delete("http://springcloud-base-kang/user/deleteUser", id);
    	return new Message<>(ErrorCode.SUCCESS);
    }
}
