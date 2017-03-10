package cn.edu.ruc.vo;

public class Behavior {
	
	private int user_id;
	private int followed_id;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFollowed_id() {
		return followed_id;
	}
	public void setFollowed_id(int followed_id) {
		this.followed_id = followed_id;
	}
	
	@Override
	public String toString() {
		return "behavior [user_id=" + user_id + ", followed_id=" + followed_id + "]";
	}
	
}
