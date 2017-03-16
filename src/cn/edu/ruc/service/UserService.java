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
	
    //设置用户信息_By Rykie
	public void SetUserInfo(User user) {
		userMapper.UpdateUserInfo(user);
	}
	
    //选出特定用户信息_By Rykie
	public List<User> SelectWantedUser(String u_name){	
		return userMapper.SelectWantedUser(u_name);
	}
	
    //登陆检查_By Rykie
    public int IsPasswordCorrect(String u_name, String u_pswd) { 	
    	
    	User user = new User();				
		user.setUsername(u_name);
		user.setPassword(u_pswd); 		
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
    public void AddNewUser(String u_name, String u_pswd) {
    	
		User user = new User();
		user.setUsername(u_name);
		user.setPassword(u_pswd);
		userMapper.InsertToUser(user);
	}
    
    
}
