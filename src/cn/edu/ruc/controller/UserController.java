package cn.edu.ruc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jmx.snmp.SnmpStringFixed;

import cn.edu.ruc.service.UserService;
import algo.*;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//登陆_By 若杨
		@RequestMapping("/login")
		@ResponseBody
		public Map<String, Object> IsUserCorrect(HttpServletRequest request,HttpSession httpSession, 
				@RequestParam(value = "username") String username,
	            @RequestParam(value = "password") String password,
	            @RequestParam(value = "localeX",required = false) String localeX,
	            @RequestParam(value = "localeY",required = false) String localeY) throws UnsupportedEncodingException{
			
			int status = 0;
			String accessToken = "null";
			
			if(userService.IsPasswordCorrect(username, password) == 1){
				System.out.println("登录成功");
				
				httpSession.setAttribute("username_session", username);
				httpSession.setAttribute("localeX_session", localeX);
				httpSession.setAttribute("localeY_sesion", localeY);
				
				/*获取当前系统UNIX时间戳*/;
				long UNIX_Time = System.currentTimeMillis()/1000;
				String ini_token = username +UNIX_Time+EncodeToken.Hash_addkey("WelcomeToHelpal","MD5");
				/* 加密 */
				accessToken =EncodeToken.Encode(ini_token);
				
				Map<String,Object> modelMap = new HashMap<String, Object>(2);
				modelMap.put("Status",status);
				modelMap.put("accessToken",accessToken);
			
		        return modelMap;
		}else{
			System.out.println("登录失败");
			status = 1;
			String errorMsg = "用户名或密码错误";
			
			Map<String,Object> modelMap = new HashMap<String, Object>(2);
			modelMap.put("Status",status);
			modelMap.put("errorMsg",errorMsg);			
		    return modelMap;
		}							
	}
		
	//注册_By 若杨
		@RequestMapping("/signup")
		@ResponseBody
		public Map<String, Object> RegisterForUsers(HttpServletRequest request, 
				@RequestParam(value = "username") String username,
	            @RequestParam(value = "password") String password,
	            @RequestParam(value = "email",required = false) String email) throws UnsupportedEncodingException{
			int status = 0;
			String accessToken = "null";

			//test
			if(userService.IsPasswordCorrect(username, password)  == -1){//说明manager表里没有该用户，注册合理
				userService.AddNewUser(username, password,email);	
				
				/*获取当前系统UNIX时间戳*/;
				long UNIX_Time = System.currentTimeMillis()/1000;
				String ini_token = username +UNIX_Time+EncodeToken.Hash_addkey("WelcomeToHelpal","MD5");
				/* 加密 */
				accessToken =EncodeToken.Encode(ini_token);
				System.out.println("用户已成功加入！");
				
				Map<String,Object> modelMap = new HashMap<String, Object>(2);
				modelMap.put("Status",status);
				modelMap.put("accessToken",accessToken);
			
		        return modelMap;
			}
			else{ 		
				System.out.println("注册失败！");
				status = 1;
				String errorMsg = "用户名或密码错误";
				
				Map<String,Object> modelMap = new HashMap<String, Object>(2);
				modelMap.put("Status",status);
				modelMap.put("errorMsg",errorMsg);			
			    return modelMap;
			}
		
		}
		
		
		//测试_By 若杨
				@RequestMapping("/test")
				@ResponseBody
				public Map<String, Object> JustTest(){
					Map<String, Object > modelMap = new HashMap<String, Object>(2);
					modelMap.put("Status","111");
					modelMap.put("accessToken","222");
					return modelMap;
				}
}
