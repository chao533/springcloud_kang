package com.kang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.common.exception.ServiceException;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.config.fdfs.FdfsConfig;
import com.kang.mapper.UserMapper;
import com.kang.model.User;
import com.kang.service.UserService;

import cn.hutool.core.lang.Assert;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FdfsConfig fdfsConfig;
	
	@Override
	public Message<?> getUserInfo(Long id) {
		Assert.notNull(id,"参数错误");
		User userParam = new User();
		userParam.setId(id);
		User user = userMapper.selectOne(userParam);
		if(user != null) {
			user.setIcon(fdfsConfig.getFullPush(user.getIcon()));
		}
		return new Message<>(ErrorCode.SUCCESS,user);
	}

	@Override
	public Message<?> getUserList(User user,int pageNo,int pageSize) {
		Page<User> page = PageHelper.startPage(pageNo, pageSize);
		Page<User> datas = (Page<User>) userMapper.select(user);
		datas.setTotal(page.getTotal());
		PageInfo<User> pageInfo = new PageInfo<>(datas);
		pageInfo.getList().forEach(u -> {
			u.setIcon(fdfsConfig.getFullPush(u.getIcon()));
		});
		return new Message<>(ErrorCode.SUCCESS,pageInfo);
	}

	@Override
	public Message<?> addUser(User user) {
		Assert.notNull(user,"参数错误");
		Assert.notBlank(user.getLoginName(),"用户名不能为空");
		Assert.notBlank(user.getPwd(),"密码不能为空");
		
		user.setIcon(fdfsConfig.getSuffixPath(user.getIcon()));
		if(userMapper.insertSelective(user) <= 0) {
			throw new ServiceException("添加失败");
		}
		return new Message<>(ErrorCode.SUCCESS);
	}

	@Override
	public Message<?> updateUser(User user) {
		Assert.notNull(user,"参数错误");
		Assert.notNull(user.getId(),"参数错误");
		user.setIcon(fdfsConfig.getSuffixPath(user.getIcon()));
		if(userMapper.updateByPrimaryKeySelective(user) <= 0) {
			throw new ServiceException("修改失败");
		}
		return new Message<>(ErrorCode.SUCCESS);
	}

	@Override
	public Message<?> deleteUser(Long id) {
		Assert.notNull(id,"参数错误");
		if(userMapper.deleteByPrimaryKey(id) <= 0) {
			throw new ServiceException("删除失败");
		}
		return new Message<>(ErrorCode.SUCCESS);
	}

	
}
