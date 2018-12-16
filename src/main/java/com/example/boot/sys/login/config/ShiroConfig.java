package com.example.boot.sys.login.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.boot.sys.login.realms.UserRealm;

@Configuration
public class ShiroConfig {
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//添加shiro内置过滤器
		/**
		 * shiro 内置过滤器，可以实现权限相关的拦截器
		 * 常用的过滤器：
		 *     anon：无需认证（登录）可以访问
		 *     authc：必须认证才可以访问
		 *     user：如果使用rememberMe的功能可以直接访问
		 *     perms：该资源必须得到资源权限才可以访问
		 *     role：该资源比逊得到角色权限才可以访问
		 */
		Map<String,String> filterMap=new LinkedHashMap<String, String>();
		filterMap.put("/action", "anon");
		filterMap.put("/login", "anon");
		filterMap.put("/*", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		shiroFilterFactoryBean.setUnauthorizedUrl("pages/unAuth");
		return shiroFilterFactoryBean;
	}
	
	
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	
	/**
	 * 创建realm
	 */
   @Bean(name="userRealm")
   public UserRealm getRealm(){
	   return new UserRealm();
   }
}
