package com.example.boot.sys.login.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.sys.login.mapper.UserMapper;
import com.example.boot.sys.login.pojo.User;
import com.example.boot.sys.login.service.LoginService;
@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByName(String userName) {
		return userMapper.findByName(userName);
	}

}
