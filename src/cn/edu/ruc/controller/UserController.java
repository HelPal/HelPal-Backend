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
import javax.xml.ws.Response;

import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.swing.internal.plaf.metal.resources.metal;

import cn.edu.ruc.dao.SkillMapper;
import cn.edu.ruc.service.*;
import cn.edu.ruc.vo.*;
import algo.*;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SkillService skillService;
	@Autowired
	private InterestService interestService;
	
	int status = 0;
	String accessToken = "null";
	String errorMsg ="null";
	
	
	//1.1登陆_By Rykie
		@RequestMapping("/login")
		@ResponseBody
		public Map<String, Object> IsUserCorrect(HttpServletRequest request,HttpSession httpSession, HttpServletResponse response,
				@RequestParam(value = "username") String username,
	            @RequestParam(value = "password") String password,
	            @RequestParam(value = "localeX",required = false) String localeX,
	            @RequestParam(value = "localeY",required = false) String localeY) throws UnsupportedEncodingException{
			response.setHeader("Access-Control-Allow-Origin", "*");
			
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
			errorMsg = "用户名或密码错误";
			
			Map<String,Object> modelMap = new HashMap<String, Object>(2);
			modelMap.put("Status",status);
			modelMap.put("errorMsg",errorMsg);			
		    return modelMap;
		}							
	}
		
	//1.2注册_By Rykie
		@RequestMapping("/signup")
		@ResponseBody		
		public Map<String, Object> RegisterForUsers(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "username") String username,
	            @RequestParam(value = "password") String password) throws UnsupportedEncodingException{
			response.setHeader("Access-Control-Allow-Origin", "*");		

			if(userService.IsPasswordCorrect(username, password)  == -1){//说明user表里没有该用户，注册合理
				userService.AddNewUser(username, password);	
				
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
				errorMsg = "用户名已存在！";
				
				Map<String,Object> modelMap = new HashMap<String, Object>(2);
				modelMap.put("Status",status);
				modelMap.put("errorMsg",errorMsg);			
			    return modelMap;
			}
		
		}
		
		//1.4. 获取用户信息_By Rykie
		@RequestMapping("/info")
		@ResponseBody
		public Map<String, Object> GetUserInfo(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(value = "username") String username){
			response.setHeader("Access-Control-Allow-Origin", "*");
			
			List<User> wanted_user = userService.SelectWantedUser(username);		
			
			if(wanted_user.size() == 1){			
				List<Skill> user_skill = skillService.SelectUserSkill(wanted_user.get(0).getUser_id());
				List<Interest> user_interest = interestService.SelectUserInterest(wanted_user.get(0).getUser_id());
				Map<String,Object> modelMap = new HashMap<String, Object>(3);
				modelMap.put("Status",status);
				modelMap.put("User",wanted_user);
				modelMap.put("Skills",user_skill);
				modelMap.put("Interests",user_interest);
				return modelMap;
			}
			else{
				status = 1;
				errorMsg = "没有查到该用户信息！";
				Map<String,Object> modelMap = new HashMap<String, Object>(2);
				modelMap.put("Status",status);
				modelMap.put("errorMsg",errorMsg);
				return modelMap;
			}	
			
		}
		
		//1.5. 设置用户信息_By Rykie
		@RequestMapping("/setInfo")
		@ResponseBody
		public Map<String, Object> SetUserInfo(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(value = "accessToken") String accessToken,
				@RequestParam(value = "gender") String gender,
				@RequestParam(value = "age") String age,
				@RequestParam(value = "star_signs") String star_signs,
				@RequestParam(value = "motto") String motto){
			response.setHeader("Access-Control-Allow-Origin", "*");
			
			
			
			
			
			return null;
			
			
		}
		
		
		
		
		
		/*测试_By 若杨
				@RequestMapping("/test")
				@ResponseBody
				public Map<String, Object> JustTest(){
					Map<String, Object > modelMap = new HashMap<String, Object>(2);
					modelMap.put("Status","111");
					modelMap.put("accessToken","222");
					return modelMap;
				}*/
}
