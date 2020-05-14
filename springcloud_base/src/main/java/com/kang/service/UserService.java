package com.kang.service;

import com.kang.common.msg.Message;
import com.kang.model.User;

/**
 * <p>Title: UserService</p>
 * <p>Description: 用户操作接口<p>
 * @author ChaoKang
 * @date 2020年5月14日
 */
public interface UserService {

	/**
	 * <p>Title: getUserInfo</p>
	 * <p>Description: 获取用户详情</p>
	 * @param @param id
	 * @param @return
	 */
	Message<?> getUserInfo(Long id);
	
	/**
	 * <p>Title: getUserList</p>
	 * <p>Description: 获取用户列表</p>
	 * @param @param user
	 * @param @return
	 */
	Message<?> getUserList(User user);
	
	/**
	 * <p>Title: addUser</p>
	 * <p>Description: 添加用户信息</p>
	 * @param @param user
	 * @param @return
	 */
	Message<?> addUser(User user);
	
	/**
	 * <p>Title: updateUser</p>
	 * <p>Description: 修改用户信息</p>
	 * @param @param user
	 * @param @return
	 */
	Message<?> updateUser(User user);
	
	/**
	 * <p>Title: deleteUser</p>
	 * <p>Description: 删除用户信息</p>
	 * @param @param id
	 * @param @return
	 */
	Message<?> deleteUser(Long id);
}
