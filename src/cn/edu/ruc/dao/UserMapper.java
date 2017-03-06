package cn.edu.ruc.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.edu.ruc.vo.User;;

public interface UserMapper {

	public List<User> SelectUserList_All();


}
