package com.example.boot.sys.login.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/action")
	public String action(){
		return "pages/login";
	}
	
	
	/**
	 * 登录逻辑处理
	 */
	@RequestMapping("/login")
	public String login(String userName,String password,Model model){
		/**
		 * 使用shiro编写认证操作
		 */
		//1.获取Subject
		Subject subject=SecurityUtils.getSubject();
		//2.封装用户数据
		UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
		//执行登录方法
		try {
			//只要没有异常就代表登录成功
			subject.login(token);
			return "pages/frame";
		} catch (UnknownAccountException e) {
			//登录失败：用户名不存在
			model.addAttribute("msg","用户名不存在!");
			return "pages/login";
		}catch (IncorrectCredentialsException e) {
			//登录失败：密码错误
			model.addAttribute("msg","密码错误!");
			return "pages/login";
		}
		
	}

}
