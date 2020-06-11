package com.kitri.pos;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kitri.pos.db.DBManager;

//��������Ҷ� �޾ƾ��ϴ� ��
//���� ID, ���� �н�����, �̸� , ����

public class UserDao {

	// ȸ������Ʈ Ŭ����

	UserDto userDto;
	// DB����� �ʿ�
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// �⺻������
	public UserDao() {

	}

// �α��� ��� �˻�
	public boolean pass(String id, String pw) {

//		boolean result = false;
//		String pass;

		con = DBManager.getConnection();


		try {

			String select = "SELECT user_code, pw, id, authority FROM members where id = ? and pw = ?";
			ps = con.prepareStatement(select);

			ps.setString(1, id);
			ps.setString(2, pw);
		
			
			System.out.println(id);
			System.out.println(pw);
	
			rs = ps.executeQuery();

//			System.out.println(rs);
			while (rs.next()) {
			
				userDto = new UserDto();
				
				userDto.setUserCode(rs.getInt(1));
				userDto.setPw(rs.getString(2));
				userDto.setId(rs.getString(3));
				userDto.setAuthority(rs.getString(4));
				
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, con);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;
	}

	// ����Ʈ�� ���� ������ ���´�.

	// ȸ�� �˻�
	public Vector<UserDto> getMemberList() {
		// �������� ���̺� �ִ� ���� : �����ڵ�, �н�����, ���̵�, ����, �̸�
		Vector<UserDto> row = new Vector<UserDto>();

		con = DBManager.getConnection();

		String select = "select * " + " from members " + " order by user_code asc";

		try {

			ps = con.prepareStatement(select);
			rs = ps.executeQuery();

			while (rs.next()) {

				int USER_CODE_SEQ = rs.getInt(1);
				String pw = rs.getString(2);
				String id = rs.getString(3);
				String authority = rs.getNString(4);
				String name = rs.getString(5);

				userDto = new UserDto();

				userDto.setUserCode(USER_CODE_SEQ);
				userDto.setPw(pw);
				userDto.setId(id);
				userDto.setAuthority(authority);
				userDto.setName(name);

				row.add(userDto);
//				row.clear();
			}

		} catch (SQLException e1) {
			System.out.println("���̺� ��� ����");
			e1.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, con);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return row;

	}

	// ȸ������
	public boolean updateMember(UserDto userDto) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String update = "update members set name = ?, pw = ?, authority = ? where id = ?";

		try {

			ps = con.prepareStatement(update);

			ps.setString(1, userDto.getName());
			ps.setString(2, userDto.getPw());
			ps.setString(3, userDto.getAuthority());
			ps.setString(4, userDto.getId());
//			ps.setString(4, userDto.getPw());

			int r = ps.executeUpdate();

			if (r > 0) {
				System.out.println("DB ������ �Ϸ�Ǿ����ϴ�.");
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("DB ������ �����߽��ϴ�.");
			e.printStackTrace();
		} finally {
			DBManager.dbClose(ps, con);
		}
		return result;
	}

	public void userSelectAll(DefaultTableModel tm) throws SQLException {

		con = DBManager.getConnection();

		String select = "select *" + "from members " + "order by user_code asc";

		try {
			ps = con.prepareStatement(select);
			rs = ps.executeQuery();

			for (int i = 0; i < tm.getRowCount();) {
				tm.removeRow(i);
			}

			while (rs.next()) {
				Object data[] = {

						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5)

				};

				tm.addRow(data);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DBManager.dbClose(ps, con);
		}

	}

	// ȸ����������
	public boolean deleteMember(String id) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String delete = "delete" + " from members " + " where id = ?";

		try {

			ps = con.prepareStatement(delete);

			ps.setString(1, id);

			int r = ps.executeUpdate();

			if (r > 0) {
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			DBManager.dbClose(ps, con);
		}
		return result;
	}

	// ȸ�����
	public boolean insertMember(UserDto userdto) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String insert = "insert into members(user_code, pw, id, authority, name) "
				+ "values(USER_CODE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {

			ps = con.prepareStatement(insert);

			ps.setString(1, userdto.getPw());
			ps.setString(2, userdto.getId());
			ps.setString(3, userdto.getAuthority());
			ps.setString(4, userdto.getName());

			int r = ps.executeUpdate(); // ���� >> ����

			if (r > 0) {
				System.out.println("ȸ������ ���� ");
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DBManager.dbClose(ps, con);
		}
		return result;
	}

}
