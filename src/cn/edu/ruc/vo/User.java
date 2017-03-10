package cn.edu.ruc.vo;


public class User {
	private int user_id;
	private String username;
	private String password;
	private String email;
	private String gender;
	private String motto;
	private String avatarUrl;
	public int getUserid() {
		return user_id;
	}
	public void setUserid(int userid) {
		this.user_id = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", gender=" + gender +  ", motto=" + motto + ", avatarUrl=" + avatarUrl + "]";
	}
	
	
}
