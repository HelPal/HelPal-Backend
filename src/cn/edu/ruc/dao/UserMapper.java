package cn.edu.ruc.dao;
import java.util.List;
import cn.edu.ruc.vo.User;

public interface UserMapper {

	public List<User> SelectWantedUser(String u_name);
    public List<User> getMatchedUser(User user);
    public void InsertToUser(User user);

}
