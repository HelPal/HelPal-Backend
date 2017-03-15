package cn.edu.ruc.vo;


public class User {
	private int user_id;
	private String username;
	private String password;
	private String gender;
	private int age;
	private String star_signs;
	private String motto;
	private String avatarUrl;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_Id(int user_Id) {
		this.user_id = user_Id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStar_signs() {
		return star_signs;
	}
	public void setStar_signs(String star_signs) {
		this.star_signs = star_signs;
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
		return "User [user_Id=" + user_id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", age=" + age + ", star_signs=" + star_signs + ", motto=" + motto + ", avatarUrl=" + avatarUrl + "]";
	}
	
	
}
