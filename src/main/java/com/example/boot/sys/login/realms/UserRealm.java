package com.example.boot.sys.login.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.boot.sys.login.pojo.User;
import com.example.boot.sys.login.service.LoginService;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	private LoginService loginService;
    /**
     * 授权执行逻辑
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("开始执行授权逻辑!");
		return null;
	}

	
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("开始执行认证逻辑!");
		//假设数据库用户名密码
//		String userName="wanghongwei";
//		String password="123";
		//编写shiro判断逻辑，判断用户名和密码
		//1判断用户名
		UsernamePasswordToken token=(UsernamePasswordToken)arg0;
		
		User user=loginService.findByName(token.getUsername());
		
		if(user==null){
			//用户名不存在
			return null;//shiro底层会抛出UnknownAccountException
		}
		//2.判断密码
		//参数1：需要返回给subject.login(token)方法的一些数据
		//参数2：用户密码
		//参数3：shiro的名字
		return new SimpleAuthenticationInfo("",user.getPassword(),"");
	}

}
