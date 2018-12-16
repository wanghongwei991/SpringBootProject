package com.example.boot.sys.login.service;

import com.example.boot.sys.login.pojo.User;

public interface LoginService {
	User findByName(String userName);
}
