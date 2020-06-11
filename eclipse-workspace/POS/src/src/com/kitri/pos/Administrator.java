package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

public class Administrator extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ForcePos forcePos;
	private JPanel contentPane; // ��ü ������
	private JTextField userTf; // ���̵� �Է�
	private JTextField passTf; // �н����� �Է�
	private JTextField nameTf; // �̸� �Է�
	private JTextField notice; // ����â
	private ForcePos frame; // ����������
	private UserDto userDto; //Userdto Ŭ���� 
	private UserDao userDao; //UserDao Ŭ����
	private JComboBox authority; //��� ����
	private JComboBox authorityUp;// ���� ����
	Vector<UserDto> data;
	Vector<String> userColumn;
	String auth; // �޺��ڽ� ���ÿ� ���� ���� ����
	private JPanel pMonitor;
	private JTable table;
	private JTextField upuserTF;
	private JTextField upassTf;
	private JTextField unameTf;
	private boolean result; // �μ�Ʈ ����� ����
	private DefaultTableModel tm;

// String colName[] = { "�����ڵ�", "", "", "", "" };
//	Object data[][] = { { "1", "������", "�ְ�1" }, { "2", "�����", "�߰�1" }, { "3", "�ɱ״�", "�ְ�2" },
//			{ "4", "�־Ʒ�", "�߰�2" } };

	// ȸ����� �г�
	JPanel pRegister = new JPanel();
	// ȸ�����̺� �г�
	JPanel pTable = new JPanel();
	// ȸ������ �г�
	JPanel ppRegister;

	CardLayout card = new CardLayout();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					Administrator administrator = new Administrator();
					administrator.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public void showFrameTest() {
//		frame1.setVisible(true);
//		frame.dispose();
//	}

	public static void tableCellCenter(JTable table) {

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺��������� ����

		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��

		TableColumnModel tcm = table.getColumnModel(); // ������ ���̺��� �÷����� ������

		// ��ü ���� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	// ������
	public void showFrame() {
		frame = new ForcePos();
		this.setVisible(false);
		frame.setVisible(true);
	}


	public Administrator() {

		setTitle("Force.pos");
		setFont(new Font("���� ���", Font.BOLD, 20));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1308, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);

		JLabel titleLabel = new JLabel("Force. pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);

		notice = new JTextField();
		notice.setFont(new Font("���� ���", Font.PLAIN, 20));
		notice.setText("\uC0C1\uD488\uBA85(..)\uB294 \uC720\uD1B5\uAE30\uD55C\uC774 \uC9C0\uB0AC\uC2B5\uB2C8\uB2E4.");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);

		JLabel dateLabel = new JLabel("2019-04-01 \uC624\uD6C4 5:01");
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);

		JPanel pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255, 255, 255));
		pMainBtn.setBounds(0, 585, 1144, 120);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);

		JLabel idLabel = new JLabel();
		idLabel.setText("\uAD00\uB9AC\uC790");
		idLabel.setBackground(new Color(105, 105, 105));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		idLabel.setBounds(860, 0, 201, 120);
		pMainBtn.add(idLabel);

		JButton mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 120);
		pMainBtn.add(mBtnInven);

		JButton mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 120);
		pMainBtn.add(mBtnSale);

		JButton mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 120);
		pMainBtn.add(mBtnCalc);

		JButton mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 120);
		pMainBtn.add(mBtnStat);

		JButton mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 120);
		pMainBtn.add(mBtnAccount);

		JPanel pSellFunction = new JPanel();
		pSellFunction.setBackground(new Color(0, 0, 128));
		pSellFunction.setBounds(1144, 50, 164, 655);
		contentPane.add(pSellFunction);
		pSellFunction.setLayout(null);

		// ������� ��ư
		JButton userInsert = new JButton("\uC720\uC800\uB4F1\uB85D");
		userInsert.setForeground(new Color(255, 255, 255));
		userInsert.setBackground(new Color(0, 0, 128));
		userInsert.setFont(new Font("���� ���", Font.BOLD, 20));
		userInsert.setBounds(0, 10, 164, 120);
		pSellFunction.add(userInsert);

		// �������� ��ư
		JButton userUpdate = new JButton("\uC720\uC800\uC218\uC815");
		userUpdate.setBackground(new Color(100, 149, 237));
		userUpdate.setForeground(new Color(255, 255, 255));
		userUpdate.setFont(new Font("���� ���", Font.BOLD, 20));
		userUpdate.setBounds(0, 130, 164, 120);
		pSellFunction.add(userUpdate);

		// �������� ��ư
		JButton userDelete = new JButton("\uC720\uC800\uC0AD\uC81C");
		userDelete.setBackground(new Color(0, 0, 128));
		userDelete.setForeground(new Color(255, 255, 255));
		userDelete.setFont(new Font("���� ���", Font.BOLD, 20));
		userDelete.setBounds(0, 260, 164, 120);
		pSellFunction.add(userDelete);

		JButton sBtnPdChange = new JButton("\uCD9C\uACB0");
		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 390, 164, 120);
		pSellFunction.add(sBtnPdChange);

		JButton logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout.setBackground(new Color(255, 69, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.setFont(new Font("���� ���", Font.BOLD, 20));
		logout.setBounds(0, 520, 164, 120);
		pSellFunction.add(logout);

		pMonitor = new JPanel();
		pMonitor.setSize(new Dimension(1144, 533));
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1144, 533);
		contentPane.add(pMonitor);

		pRegister.setBackground(SystemColor.desktop);
		pRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		pRegister.setLayout(null);

		ppRegister = new JPanel();
		ppRegister.setBackground(Color.ORANGE);
		pMonitor.add(ppRegister, "name_19549728576459");
		ppRegister.setLayout(null);

		JPanel prInput = new JPanel();
		prInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		prInput.setBounds(451, 10, 356, 513);
		prInput.setLayout(new GridLayout(11, 1, 0, 0));
		pRegister.add(prInput);
//---------------------------------------------------------------------//
//		String header[] = {"�����ڵ�", "�н�����", "���̵�", "����", "�̸�"};

		// ȭ�鿡 �ѷ��ִ� ���̺� !!!����
		userDao = new UserDao();
		data = userDao.getMemberList();
//		data.clear();
//		data = userDao.getMemberList();

		// �÷���
		userColumn = new Vector<String>();
		
		userColumn.addElement("�����ڵ�");
		userColumn.addElement("�н�����");
		userColumn.addElement("���̵�");
		userColumn.addElement("����");
		userColumn.addElement("�̸�");

		tm = new DefaultTableModel(userColumn, 0);
		table = new JTable(tm);

		JScrollPane scrollPane = new JScrollPane(table);
		pTable.add(scrollPane);
		table.setRowHeight(60);
		tableCellCenter(table);
		pTable.setLayout(null);
		scrollPane.setBounds(0, 5, 1144, 528);

		int size = data.size();

		for (int i = 0; i < size; i++) {
			// ��
			Vector<String> row = new Vector<String>();

			// ���ڸ� ���ڷ� ��ȯ �࿡ �߰�
			row.addElement(data.get(i).getUserCode() + "");
			row.addElement(data.get(i).getPw());
			row.addElement(data.get(i).getId());
			row.addElement(data.get(i).getAuthority());
			row.addElement(data.get(i).getName());

			tm.addRow(row);

		}
		
		// ī�巹�̾ƿ����.
		pMonitor.setLayout(card);
		pMonitor.add("pTable", pTable);
		pMonitor.add("pRegister", pRegister);
		pMonitor.add("ppRegister", ppRegister);
		card.show(pMonitor, "pTable");

		JPanel panel = new JPanel();
		prInput.add(panel);

		JLabel userIdLabel = new JLabel("\uC720\uC800ID");
		userIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userIdLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		userIdLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInput.add(userIdLabel);

		// ȸ����� - �������̵��Է�
		userTf = new JTextField();
		userTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(userTf);
		userTf.setColumns(10);
		JLabel passWLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		passWLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		passWLabel_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInput.add(passWLabel_1);

		// ȸ����� - �н������Է�
		passTf = new JTextField();
		passTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(passTf);
		passTf.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInput.add(lblNewLabel_2);

		// ȸ����� - �̸��Է�
		nameTf = new JTextField();
		nameTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(nameTf);
		nameTf.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("\uAD8C\uD55C");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInput.add(lblNewLabel_3);

		// ���ѹ迭

		authority = new JComboBox();
		authority.addItem("������");
		authority.addItem("����");
		prInput.add(authority);

		// �Ʒ���ư�г�
		JPanel pB = new JPanel();
		prInput.add(pB);
		pB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Ȯ�ι�ư
		JButton ok = new JButton("\uD655\uC778");
		ok.setMargin(new Insets(2, 20, 2, 20));
		ok.setHorizontalTextPosition(SwingConstants.CENTER);
		ok.setFont(new Font("���� ���", Font.PLAIN, 20));
		ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		pB.add(ok);

		// ��ҹ�ư
		JButton cancel = new JButton("\uCDE8\uC18C");
		cancel.setMargin(new Insets(2, 20, 2, 20));
		cancel.setBackground(new Color(255, 99, 71));
		cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		cancel.setFont(new Font("���� ���", Font.BOLD, 20));
		pB.add(cancel);

		// ȸ������ - ȭ��
		JPanel prInsert = new JPanel();
		prInsert.setBounds(451, 10, 356, 513);
		prInsert.setBorder(new LineBorder(new Color(0, 0, 0)));
		ppRegister.add(prInsert);
		prInsert.setLayout(new GridLayout(11, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		prInsert.add(panel_2);

		JLabel upuserL = new JLabel("\uC720\uC800ID");
		upuserL.setHorizontalAlignment(SwingConstants.CENTER);
		upuserL.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInsert.add(upuserL);

		upuserTF = new JTextField();
		upuserTF.setHorizontalAlignment(SwingConstants.CENTER);
		upuserTF.setFont(new Font("���� ���", Font.PLAIN, 20));
		upuserTF.setEnabled(false);
		upuserTF.setDragEnabled(true);
		upuserTF.setColumns(10);
		prInsert.add(upuserTF);

		JLabel uppassL = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		uppassL.setHorizontalAlignment(SwingConstants.CENTER);
		uppassL.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInsert.add(uppassL);

		upassTf = new JTextField();
		upassTf.setHorizontalAlignment(SwingConstants.CENTER);
		upassTf.setColumns(10);
		prInsert.add(upassTf);

		JLabel upnameL = new JLabel("\uC774\uB984");
		upnameL.setHorizontalAlignment(SwingConstants.CENTER);
		upnameL.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInsert.add(upnameL);

		unameTf = new JTextField();
		unameTf.setHorizontalAlignment(SwingConstants.CENTER);
		unameTf.setColumns(10);
		prInsert.add(unameTf);

		JLabel upauthL = new JLabel("\uAD8C\uD55C");
		upauthL.setHorizontalAlignment(SwingConstants.CENTER);
		upauthL.setFont(new Font("���� ���", Font.PLAIN, 20));
		prInsert.add(upauthL);

		// ����ȭ�� �޺��ڽ�
		authorityUp = new JComboBox();
		authorityUp.addItem("������");
		authorityUp.addItem("����");
		prInsert.add(authorityUp);

		JPanel panel_3 = new JPanel();
		prInsert.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button = new JButton("\uC218\uC815");
		button.setMargin(new Insets(2, 20, 2, 20));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setFont(new Font("���� ���", Font.PLAIN, 20));
		button.setAlignmentX(0.5f);
		panel_3.add(button);

		JButton button_1 = new JButton("\uCDE8\uC18C");
		button_1.setMargin(new Insets(2, 20, 2, 20));
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		button_1.setFont(new Font("���� ���", Font.BOLD, 20));
		button_1.setBackground(new Color(255, 99, 71));
		panel_3.add(button_1);

		// �̺�Ʈ ������ ���
		userInsert.addActionListener(this);
		userUpdate.addActionListener(this);
		//
		userDelete.addActionListener(this);
		logout.addActionListener(this);
		authority.addActionListener(this);
		authorityUp.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		button.addActionListener(this);
		button_1.addActionListener(this);
		
		//
		table.addMouseListener(ms);
	}

	// ȸ�����â�� �Էµ� ���� ������
	public UserDto getViewData() {

		userDto = new UserDto();

		userDto.setPw(passTf.getText());
		userDto.setId(userTf.getText());
		userDto.setName(nameTf.getText());
		userDto.setAuthority(auth);

		return userDto;

	}

	// ȸ�� ����â�� �Էµ� ���� ������
	public UserDto getViewUpdata() {

		userDto = new UserDto();

		userDto.setId(upuserTF.getText());
		userDto.setPw(upassTf.getText());
		userDto.setName(unameTf.getText());
		userDto.setAuthority(auth);

		return userDto;

	}


	public boolean isUserId() {

		String user = userTf.getText().trim();
		String pass = passTf.getText().trim();
		String name = nameTf.getText().trim();

		if (user.length() > 10) {
			JOptionPane.showMessageDialog(this, "���̵�� 10�� �̸����� ���������մϴ�.", "ID���� ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "������ �ȵǿ�!!!");
				result = false;
		} else {
				result = true;
		}
		
		return result;
	}

	// ȸ����� ��ȿ���˻�.
	private void insertUser() {

		getViewData();

		if (isUserId()) {
			JOptionPane.showMessageDialog(this, "����� �Ϸ�Ǿ����ϴ�. �����մϴ�.");
			card.show(pMonitor, "pTable");
		} else {
			JOptionPane.showMessageDialog(this, "����� ���еǾ����ϴ�.");
			return;
		}
	}

	private void updateUser() {

		userDao = new UserDao();
		UserDto re = getViewUpdata();

		try {
			boolean result;
			
			result = userDao.updateMember(re);

			if (result) {
				JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
				card.show(pMonitor, "pTable");
			} else {
				JOptionPane.showMessageDialog(this, "������ ���еǾ����ϴ�.");
			 	return;
			}
			
			userDao.userSelectAll(tm);
			
		} catch (SQLException e) {
			System.out.println("������Ʈ ����");
			e.printStackTrace();
		}
	}

	private void deleteUser() {
		// ���� ��ȣ�� �̾ƿ�.
		int numberRow = table.getSelectedRow();
		// ���� ��ȣ�� �̾ƿ�.
//		int numberColumn = table.getSelectedColumn();
		String id = (String) tm.getValueAt(numberRow, 2);

		if (id.length() == 0) {
			JOptionPane.showMessageDialog(this, "id�� Ŭ�����ּ���.");
			return;
		}

		userDao = new UserDao();

		try {
			boolean result;
			result = userDao.deleteMember(id);

			if (result) {
				JOptionPane.showMessageDialog(this, "�����Ϸ�");
			} else {
				JOptionPane.showMessageDialog(this, "��������");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// ���� ��� �ؽ�Ʈ�ʵ� �ʱ�ȭ
	public void tfClear() {
		userTf.setText("");
		passTf.setText("");
		nameTf.setText("");
	}

	// ���� ���� �ؽ�Ʈ�ʵ� �ʱ�ȭ
	public void tfUClear() {
		upassTf.setText("");
		upuserTF.setText("");
		unameTf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getActionCommand();
		Object ob2 = e.getSource();

		//�޺��ڽ����� ���� ������ �� ���� ����
		if (ob2 == authority) {
			String str = authority.getSelectedItem().toString();
//			System.out.println(str);
			if(str.equals("������")) {
				auth = "T";
			}else if(str.equals("����")){
				auth = "F";
			}
		}
			if(ob2 == authorityUp) {
			String str = authorityUp.getSelectedItem().toString();
			System.out.println(str);
				if(str.equals("������")) {
					auth = "T";
				}else if(str.equals("����")){
					auth = "F";
				}	
				
				System.out.println(auth);
		}


		// ȸ������̶������.
		if (ob.equals("�������")) {
			card.show(pMonitor, "pRegister");
//			isSelect(); //���� ����
			tfClear();
		}

		// ȸ�������̶�� ����.
		if (ob.equals("��������")) {
			tfUClear();
			// ���� ��ȣ�� �̾ƿ�.
			int numberRow = table.getSelectedRow();
			
			// ���� ��ȣ�� �̾ƿ�.
//			int numberColumn = table.getSelectedColumn();
			if (table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "���̺��� Ŭ�����ּ���.");
			} else {
				String id = (String) tm.getValueAt(numberRow, 2);
				card.show(pMonitor, "ppRegister");
				upuserTF.setText(id);
			}
		}

		// ������ ���������� ����.
		if (ob.equals("��������")) {

			UserDao userDao = new UserDao();

			if (table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "���̺��� Ŭ�����ּ���.");
			
		} else {
			int x = JOptionPane.showConfirmDialog(this, "���� ���� �Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
			if (x == JOptionPane.OK_OPTION) {
				deleteUser();
				try {
					userDao.userSelectAll(tm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "������ ����Ͽ����ϴ�.");
				}
			}
		}


		if (ob.equals("����")) {
			updateUser();
		}

		// Ȯ���� ������ �������â
		if (ob.equals("Ȯ��")) {

			insertUser(); // ��ȿ�� �˻�

			UserDto re = getViewData(); // ���� �Ѿ ������ userDto�� ����.
			userDao = new UserDao(); // userDao ��ü ����

			if (result) { // �����Ͱ� �Ѿ� ���ٸ�.
				try {
					userDao.getMemberList(); // select �� ����.
					userDao.insertMember(re); // insert�� ����.
					userDao.userSelectAll(tm);
//					tableRefresh();
				} catch (SQLException e1) {
					System.out.println("Ȯ�� ����");
					e1.printStackTrace();
				}
			} else {
				result = false;
				return;
			}

		}

		// ��ҹ�ư�� ������ ���ÿ� �ٽ� ���̺�ȭ������.
		if (ob.equals("���")) {
			System.out.println("��ҹ�ư���");
			card.show(pMonitor, "pTable");
		}

		// ���״�� �α׾ƿ� �������������� �Ѿ.
		if (ob.equals("�α׾ƿ�")) {
			this.setVisible(false);
			forcePos = new ForcePos();
			forcePos.setVisible(true);
		}

	}
	
	MouseAdapter ms = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			super.mouseClicked(e);
			
			table = (JTable) e.getComponent();
			tm = (DefaultTableModel) table.getModel();
			
		}
		
	};
}
