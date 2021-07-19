package kr.co.domain;

public class LoginDTO {

	private String userId;
	private String userPw;
	private String userName;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(String userId, String userPw, String userName) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
	
	
}
