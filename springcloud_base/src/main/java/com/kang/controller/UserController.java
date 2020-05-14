package com.kang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.Message;
import com.kang.model.User;
import com.kang.service.UserService;
/**
 * <p>Title: UserController</p>
 * <p>Description: 用户操作<p>
 * @author ChaoKang
 * @date 2020年5月14日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * <p>Title: getUserInfo</p>
	 * <p>Description: 获取用户信息</p>
	 * @param @param id
	 * @param @return
	 */
    @GetMapping("/{id:\\d+}")
    public Message<?> getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    /**
     * <p>Title: getUserList</p>
     * <p>Description: 获取用户列表</p>
     * @param @param user
     * @param @return
     */
    @GetMapping("/getUserList")
    public Message<?> getUserList(User user) {
        return userService.getUserList(user);
    }

    /**
     * <p>Title: addUser</p>
     * <p>Description: 添加用户信息</p>
     * @param @param user
     * @param @return
     */
    @PostMapping("/addUser")
    public Message<?> addUser(@RequestBody User user) {
    	return userService.addUser(user);
    }

    /**
     * <p>Title: update</p>
     * <p>Description: 修改用户信息</p>
     * @param @param user
     * @param @return
     */
    @PutMapping("/updateUser")
    public Message<?> updateUser(@RequestBody User user) {
    	return userService.updateUser(user);
    }

    /**
     * <p>Title: delete</p>
     * <p>Description: 删除用户信息</p>
     * @param @param id
     * @param @return
     */
    @DeleteMapping("/{id:\\d+}")
    public Message<?> deleteUser(@PathVariable Long id) {
    	return userService.deleteUser(id);
    }
}
