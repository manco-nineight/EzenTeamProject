package kr.co.domain;

import java.io.Serializable;

public class MemberDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String userBirthday;
	private String userAddress;
	private String userRegdate;
	private String userUpdatedate;
	private int userGrade;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String userId, String userPw, String userName, String userEmail, String userBirthday,
			String userAddress, String userRegdate, String userUpdatedate, int userGrade) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userRegdate = userRegdate;
		this.userUpdatedate = userUpdatedate;
		this.userGrade = userGrade;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}

	public String getUserUpdatedate() {
		return userUpdatedate;
	}

	public void setUserUpdatedate(String userUpdatedate) {
		this.userUpdatedate = userUpdatedate;
	}

	public int getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
		result = prime * result + ((userBirthday == null) ? 0 : userBirthday.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + userGrade;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPw == null) ? 0 : userPw.hashCode());
		result = prime * result + ((userRegdate == null) ? 0 : userRegdate.hashCode());
		result = prime * result + ((userUpdatedate == null) ? 0 : userUpdatedate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		if (userBirthday == null) {
			if (other.userBirthday != null)
				return false;
		} else if (!userBirthday.equals(other.userBirthday))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userGrade != other.userGrade)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPw == null) {
			if (other.userPw != null)
				return false;
		} else if (!userPw.equals(other.userPw))
			return false;
		if (userRegdate == null) {
			if (other.userRegdate != null)
				return false;
		} else if (!userRegdate.equals(other.userRegdate))
			return false;
		if (userUpdatedate == null) {
			if (other.userUpdatedate != null)
				return false;
		} else if (!userUpdatedate.equals(other.userUpdatedate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userBirthday=" + userBirthday + ", userAddress=" + userAddress + ", userRegdate="
				+ userRegdate + ", userUpdatedate=" + userUpdatedate + ", userGrade=" + userGrade + "]";
	}

}
