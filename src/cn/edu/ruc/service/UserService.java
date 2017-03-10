package cn.edu.ruc.service;

import java.text.DecimalFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.ruc.dao.*;
import cn.edu.ruc.vo.*;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public int user_num;
	
  //选出所有的用户信息
    public int SelectUserList_All(){
		
		List<User> users = new ArrayList<User>();
		
		try{
			users = userMapper.SelectUserList_All();
			user_num = users.size();
		}catch(Exception e){
    		e.printStackTrace();
    	}
		return user_num;
	}
    
  //登陆检查_By Rykie
    public int IsPasswordCorrect(String mnr, String psw) {
    	
    	User user = new User();				
		user.setUsername(mnr);
		user.setPassword(psw);
   
    	List<User> users  = new ArrayList<User>();
    	users = userMapper.getMatchedUser(user);
			if (users.size()<=0){
				return -1;
			}
			else{
				return 1;
			}
		}

   //添加新用户_By Rykie
    public void AddNewUser(String u_name, String u_pswd, String u_email) {
    	
		User user = new User();
		user.setUsername(u_name);
		user.setPassword(u_pswd);
		user.setEmail(u_email);
		userMapper.InsertToUser(user);
	}
    
}
