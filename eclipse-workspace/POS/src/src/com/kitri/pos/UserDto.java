package com.kitri.pos;

public class UserDto {
	
	private int user_code; // �����ڵ�
	private String pw; // �н�����
	private String id; // ���̵�
	private String authority; // ����
	private String name; // �̸�

	public int getUserCode() {
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

	public void setUserCode(int user_code) {
		this.user_code = user_code;
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
