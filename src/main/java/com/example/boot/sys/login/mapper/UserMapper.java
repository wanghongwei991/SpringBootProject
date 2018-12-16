package com.example.boot.sys.login.mapper;

import com.example.boot.sys.login.pojo.User;

public interface UserMapper {
	User findByName(String userName);
}
