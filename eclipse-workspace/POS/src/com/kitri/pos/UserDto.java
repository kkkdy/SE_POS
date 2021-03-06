package com.kitri.pos;

public class UserDto {
	
	private String user_code; // 유저코드
	private String pw; // 패스워드
	private String id; // 아이디
	private String authority; // 권한
	private String name; // 이름

	public String getUserCode() {
		return user_code;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserCode(String string) {
		this.user_code = string;
	}

	public void setId(String id) {
		this.id = id;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
