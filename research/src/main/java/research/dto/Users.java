package research.dto;

public class Users {

	private String userNo;
	private String userName;
	private String phoneNo;
	private String email;
	private String userId;
	private String userPw;
	private String userGrade;
	@Override
	public String toString() {
		return "Users [userNo=" + userNo + ", userName=" + userName + ", phoneNo=" + phoneNo + ", email=" + email
				+ ", userId=" + userId + ", userPw=" + userPw + ", userGrade=" + userGrade + "]";
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	
	
	
}
