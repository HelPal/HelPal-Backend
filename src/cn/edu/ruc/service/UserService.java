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

    
}
